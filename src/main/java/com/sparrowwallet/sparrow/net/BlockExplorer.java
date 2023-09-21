package com.sparrowwallet.sparrow.net;

import com.sparrowwallet.sparrow.io.Server;

public enum BlockExplorer {
    BLOCKSTREAM_INFO("https://esplora.groestlcoin.org"),
    GROESTLSIGHT("https://groestlsight.groestlcoin.org"),
    BLOCKBOOK("https://blockbook.groestlcoin.org"),
    NONE("http://none");

    private final Server server;

    BlockExplorer(String url) {
        this.server = new Server(url);
    }

    public Server getServer() {
        return server;
    }
}
