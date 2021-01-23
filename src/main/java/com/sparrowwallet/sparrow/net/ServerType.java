package com.sparrowwallet.sparrow.net;

public enum ServerType {
    BITCOIN_CORE("Groestlcoin Core"), ELECTRUM_SERVER("Electrum-GRS Server");

    private final String name;

    ServerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
