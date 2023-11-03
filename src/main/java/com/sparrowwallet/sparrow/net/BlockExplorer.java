package com.sparrowwallet.sparrow.net;

import com.sparrowwallet.sparrow.io.Server;

import java.util.Arrays;
import java.util.HashMap;

public enum BlockExplorer {
    ESPLORA("https://esplora.groestlcoin.org"),
    GROESTLSIGHT("https://groestlsight.groestlcoin.org"),
    BLOCKBOOK("https://blockbook.groestlcoin.org"),
    CHAINZ("https://chainz.cryptoid.info/grs"),
    NONE("http://none");

    private final Server server;
    private final String url;

    private static class Info {
        String txPath;
        String testNetUrl;

        public Info(String txPath, String testNetUrl) {
            this.txPath = txPath;
            this.testNetUrl = testNetUrl;
        }
    }

    private static final HashMap<BlockExplorer, Info> infoMap = new HashMap<>();
    static {
        infoMap.put(ESPLORA, new Info("/tx/", "https://esplora-test.groestlcoin.org"));
        infoMap.put(BLOCKBOOK, new Info("/tx/", "https://blockbook-test.groestlcoin.org"));
        infoMap.put(GROESTLSIGHT, new Info("/tx/", "https://groestlsight-test.groestlcoin.org"));
        infoMap.put(CHAINZ, new Info("/tx.dws?", "https://chainz.cryptoid.info/grs-test"));
    }

    BlockExplorer(String url) {
        this.url = url;
        this.server = new Server(url);
    }

    public static BlockExplorer from(String url) {
        return Arrays.stream(values()).filter(value -> value.url.equals(url)).findFirst().get();
    }

    public Server getServer() {
        return server;
    }

    public Server getTestnetServer() {
        return new Server(infoMap.get(this).testNetUrl);
    }

    public String getTxPath() {
        return infoMap.get(this).txPath;
    }
}
