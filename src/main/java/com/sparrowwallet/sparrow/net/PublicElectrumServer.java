package com.sparrowwallet.sparrow.net;

import com.sparrowwallet.drongo.Network;
import com.sparrowwallet.sparrow.io.Server;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum PublicElectrumServer {
    ELECTRUM1_GROESTLCOIN_ORG("electrum1.groestlcoin.org", "ssl://electrum1.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM2_GROESTLCOIN_ORG("electrum2.groestlcoin.org", "ssl://electrum2.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM3_GROESTLCOIN_ORG("electrum3.groestlcoin.org", "ssl://electrum3.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM4_GROESTLCOIN_ORG("electrum4.groestlcoin.org", "ssl://electrum4.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM5_GROESTLCOIN_ORG("electrum5.groestlcoin.org", "ssl://electrum5.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM6_GROESTLCOIN_ORG("electrum6.groestlcoin.org", "ssl://electrum6.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM7_GROESTLCOIN_ORG("electrum7.groestlcoin.org", "ssl://electrum7.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM8_GROESTLCOIN_ORG("electrum8.groestlcoin.org", "ssl://electrum8.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM9_GROESTLCOIN_ORG("electrum9.groestlcoin.org", "ssl://electrum9.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM10_GROESTLCOIN_ORG("electrum10.groestlcoin.org", "ssl://electrum10.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM11_GROESTLCOIN_ORG("electrum11.groestlcoin.org", "ssl://electrum11.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM12_GROESTLCOIN_ORG("electrum12.groestlcoin.org", "ssl://electrum12.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM13_GROESTLCOIN_ORG("electrum13.groestlcoin.org", "ssl://electrum13.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM14_GROESTLCOIN_ORG("electrum14.groestlcoin.org", "ssl://electrum14.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM15_GROESTLCOIN_ORG("electrum15.groestlcoin.org", "ssl://electrum15.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM16_GROESTLCOIN_ORG("electrum16.groestlcoin.org", "ssl://electrum16.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM17_GROESTLCOIN_ORG("electrum17.groestlcoin.org", "ssl://electrum17.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM18_GROESTLCOIN_ORG("electrum18.groestlcoin.org", "ssl://electrum18.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM19_GROESTLCOIN_ORG("electrum19.groestlcoin.org", "ssl://electrum19.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM20_GROESTLCOIN_ORG("electrum20.groestlcoin.org", "ssl://electrum20.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM21_GROESTLCOIN_ORG("electrum21.groestlcoin.org", "ssl://electrum21.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM22_GROESTLCOIN_ORG("electrum22.groestlcoin.org", "ssl://electrum22.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM23_GROESTLCOIN_ORG("electrum23.groestlcoin.org", "ssl://electrum23.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM24_GROESTLCOIN_ORG("electrum24.groestlcoin.org", "ssl://electrum24.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM25_GROESTLCOIN_ORG("electrum25.groestlcoin.org", "ssl://electrum25.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM26_GROESTLCOIN_ORG("electrum26.groestlcoin.org", "ssl://electrum26.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM27_GROESTLCOIN_ORG("electrum27.groestlcoin.org", "ssl://electrum27.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM28_GROESTLCOIN_ORG("electrum28.groestlcoin.org", "ssl://electrum28.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM29_GROESTLCOIN_ORG("electrum29.groestlcoin.org", "ssl://electrum29.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM30_GROESTLCOIN_ORG("electrum30.groestlcoin.org", "ssl://electrum30.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM31_GROESTLCOIN_ORG("electrum31.groestlcoin.org", "ssl://electrum31.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM32_GROESTLCOIN_ORG("electrum32.groestlcoin.org", "ssl://electrum32.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM33_GROESTLCOIN_ORG("electrum33.groestlcoin.org", "ssl://electrum33.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM34_GROESTLCOIN_ORG("electrum34.groestlcoin.org", "ssl://electrum34.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM35_GROESTLCOIN_ORG("electrum35.groestlcoin.org", "ssl://electrum35.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM36_GROESTLCOIN_ORG("electrum36.groestlcoin.org", "ssl://electrum36.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM37_GROESTLCOIN_ORG("electrum37.groestlcoin.org", "ssl://electrum37.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM38_GROESTLCOIN_ORG("electrum38.groestlcoin.org", "ssl://electrum38.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM39_GROESTLCOIN_ORG("electrum39.groestlcoin.org", "ssl://electrum39.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM40_GROESTLCOIN_ORG("electrum40.groestlcoin.org", "ssl://electrum40.groestlcoin.org:50002", Network.MAINNET),
    ELECTRUM_TEST1_GROESTLCOIN_ORG("electrum-test1.groestlcoin.org", "ssl://electrum-test1.groestlcoin.org:51002", Network.TESTNET),
    ELECTRUM_TEST2_GROESTLCOIN_ORG("electrum-test2.groestlcoin.org", "ssl://electrum-test2.groestlcoin.org:51002", Network.TESTNET);

    PublicElectrumServer(String name, String url, Network network) {
        this.server = new Server(url, name);
        this.network = network;
    }

    public static final List<Network> SUPPORTED_NETWORKS = List.of(Network.MAINNET, Network.TESTNET);

    private final Server server;
    private final Network network;

    public Server getServer() {
        return server;
    }

    public String getUrl() {
        return server.getUrl();
    }

    public Network getNetwork() {
        return network;
    }

    public static List<PublicElectrumServer> getServers() {
        return Arrays.stream(values()).filter(server -> server.network == Network.get()).collect(Collectors.toList());
    }

    public static boolean supportedNetwork() {
        return SUPPORTED_NETWORKS.contains(Network.get());
    }

    public static PublicElectrumServer fromServer(Server server) {
        for(PublicElectrumServer publicServer : values()) {
            if(publicServer.getServer().equals(server)) {
                return publicServer;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return server.getAlias();
    }
}
