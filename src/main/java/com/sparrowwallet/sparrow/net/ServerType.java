package com.sparrowwallet.sparrow.net;

public enum ServerType {
    BITCOIN_CORE("Bitcoin Core"), ELECTRUM_SERVER("Private Electrum-GRS"), PUBLIC_ELECTRUM_SERVER("Public Electrum-GRS");

    private final String name;

    ServerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
