package com.sparrowwallet.sparrow.net;

import com.sparrowwallet.drongo.Network;
import com.sparrowwallet.drongo.Utils;
import com.sparrowwallet.drongo.protocol.Sha256Hash;
import com.sparrowwallet.drongo.protocol.Transaction;
import com.sparrowwallet.sparrow.AppServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.List;

public enum BroadcastSource {
    // https://github.com/Blockstream/esplora/blob/master/API.md
    BLOCKSTREAM_INFO("esplora.groestlcoin.org", "https://esplora.groestlcoin.org", "http://explorerzydxu5ecjrkwceayqybizmpjjznk5izmitf2modhcusuqlid.onion") {
        @Override
        public Sha256Hash broadcastTransaction(Transaction transaction) throws BroadcastException {
            String data = Utils.bytesToHex(transaction.bitcoinSerialize());
            return postTransactionData(data);
        }

        @Override
        public List<Network> getSupportedNetworks() {
            return List.of(Network.MAINNET, Network.TESTNET);
        }

        protected URL getURL(Proxy proxy) throws MalformedURLException {
            if(Network.get() == Network.MAINNET) {
                return new URL(getBaseUrl(proxy) + "/api/tx");
            } else if(Network.get() == Network.TESTNET) {
                return new URL("https://groestlsight-test.groestlcoin.org" + "/tx/send");
            } else {
                throw new IllegalStateException("Cannot broadcast transaction to " + getName() + " on network " + Network.get());
            }
        }
    },

    // https://github.com/trezor/blockbook/blob/master/docs/api.md#send-transaction
    BLOCKBOOK("blockbook.groestlcoin.org", "https://blockbook.groestlcoin.org", "") {
        public Sha256Hash broadcastTransaction(Transaction transaction) throws BroadcastException {
            String data = Utils.bytesToHex(transaction.bitcoinSerialize());
            return postTransactionData(data);
        }

        @Override
        public List<Network> getSupportedNetworks() {
            return List.of(Network.MAINNET, Network.TESTNET, Network.SIGNET);
        }

        protected URL getURL(Proxy proxy) throws MalformedURLException {
            if(Network.get() == Network.MAINNET) {
                return new URL(getBaseUrl(proxy) + "/api/v2/sendtx/");
            } else if(Network.get() == Network.TESTNET) {
                return new URL(getBaseUrl(proxy) + "/testnet/api/tx");
            } else if(Network.get() == Network.SIGNET) {
                return new URL(getBaseUrl(proxy) + "/signet/api/tx");
            } else {
                throw new IllegalStateException("Cannot broadcast transaction to " + getName() + " on network " + Network.get());
            }
        }
    },
    GROESTLSIGHT("groestlsight.groestlcoin.org", "https://groestlsight.groestlcoin.org", "") {
        public Sha256Hash broadcastTransaction(Transaction transaction) throws BroadcastException {
            String data = Utils.bytesToHex(transaction.bitcoinSerialize());
            return postTransactionData(data);
        }

        @Override
        public List<Network> getSupportedNetworks() {
            return List.of(Network.MAINNET);
        }

        protected URL getURL(Proxy proxy) throws MalformedURLException {
            if(Network.get() == Network.MAINNET) {
                return new URL(getBaseUrl(proxy) + "/tx/send");
            } else if(Network.get() == Network.TESTNET) {
                return new URL("https://blockbook-test.groestlcoin.org" + "/api/v2/sendtx/");
            } else {
                throw new IllegalStateException("Cannot broadcast transaction to " + getName() + " on network " + Network.get());
            }
        }
    },
    MEMPOOL_BISQ_SERVICES("chainz.cryptoid.info", "https://chainz.cryptoid.info", "") {
        public Sha256Hash broadcastTransaction(Transaction transaction) throws BroadcastException {
            String data = Utils.bytesToHex(transaction.bitcoinSerialize());
            return postTransactionData(data);
        }

        @Override
        public List<Network> getSupportedNetworks() {
            return List.of(Network.MAINNET);
        }

        protected URL getURL(Proxy proxy) throws MalformedURLException {
            if(Network.get() == Network.MAINNET) {
                return new URL(getBaseUrl(proxy) + "/grs/api.dws?key=d47da926b82e&q=pushtx");
            } else if(Network.get() == Network.TESTNET) {
                return new URL(getBaseUrl(proxy) + "/grs-test/api.dws?key=d47da926b82e&q=pushtx");
            } else {
                throw new IllegalStateException("Cannot broadcast transaction to " + getName() + " on network " + Network.get());
            }
        }
    };

    private final String name;
    private final String tlsUrl;
    private final String onionUrl;

    private static final Logger log = LoggerFactory.getLogger(BroadcastSource.class);
    private static final SecureRandom secureRandom = new SecureRandom();

    BroadcastSource(String name, String tlsUrl, String onionUrl) {
        this.name = name;
        this.tlsUrl = tlsUrl;
        this.onionUrl = onionUrl;
    }

    public String getName() {
        return name;
    }

    public String getTlsUrl() {
        return tlsUrl;
    }

    public String getOnionUrl() {
        return onionUrl;
    }

    public String getBaseUrl(Proxy proxy) {
        return (proxy == null ? getTlsUrl() : getOnionUrl());
    }

    public abstract Sha256Hash broadcastTransaction(Transaction transaction) throws BroadcastException;

    public abstract List<Network> getSupportedNetworks();

    protected abstract URL getURL(Proxy proxy) throws MalformedURLException;

    public Sha256Hash postTransactionData(String data) throws BroadcastException {
        //If a Tor proxy is configured, ensure we use a new circuit by configuring a random proxy password
        Proxy proxy = AppServices.getProxy(Integer.toString(secureRandom.nextInt()));

        try {
            URL url = getURL(proxy);

            if(log.isInfoEnabled()) {
                log.info("Broadcasting transaction to " + url);
            }

            HttpURLConnection connection = proxy == null ? (HttpURLConnection)url.openConnection() : (HttpURLConnection)url.openConnection(proxy);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/plain");
            connection.setDoOutput(true);

            try(OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(data);
                writer.flush();
            }

            StringBuilder response = new StringBuilder();
            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            int statusCode = connection.getResponseCode();
            if(statusCode < 200 || statusCode >= 300) {
                throw new BroadcastException("Could not broadcast transaction, server returned " + statusCode + ": " + response);
            }

            try {
                return Sha256Hash.wrap(response.toString().trim());
            } catch(Exception e) {
                throw new BroadcastException("Could not retrieve txid from broadcast, server returned " + statusCode + ": " + response);
            }
        } catch(IOException e) {
            log.error("Could not post transaction via " + getName(), e);
            throw new BroadcastException("Could not broadcast transaction via " + getName(), e);
        }
    }

    public static final class BroadcastException extends Exception {
        public BroadcastException(String message) {
            super(message);
        }

        public BroadcastException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
