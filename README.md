# Sparrow Groestlcoin Wallet

Sparrow-GRS is a modern desktop Groestlcoin wallet application supporting most hardware wallets and built on common standards such as PSBT, with an emphasis on transparency and usability.

More information (and release binaries) can be found at https://groestlcoin.org. Release binaries are also available directly from [Github](https://github.com/Groestlcoin/sparrow/releases).

## Building

To clone this project, use

`git clone --recursive https://github.com/Groestlcoin/sparrow`

In order to build, Sparrow requires Java 14 to be installed. The release packages can be built using

`./gradlew jpackage`

When updating to the latest HEAD

`git pull --recurse-submodules`

All jar files created are reproducible builds.

## Running

If you prefer to run Sparrow directly from source, it can be launched with

`./sparrow`

Java 14 must be installed.

## Configuration

Sparrow has a number of command line options, for example to change its home folder or use testnet:

```
./sparrow -h

Usage: sparrow [options]
  Options:
    --dir, -d
      Path to Sparrow home folder
    --help, -h
      Show usage
    --network, -n
      Network to use
      Possible Values: [mainnet, testnet, regtest]
```

As a fallback, the network (mainnet, testnet or regtest) can also be set using an environment variable `SPARROW_NETWORK`. For example:

`export SPARROW_NETWORK=testnet`

A final fallback which can be useful when running the Sparrow binary is to create a file called ``network-testnet`` in the Sparrow home folder (see below) to configure the testnet network.

Note that if you are connecting to an Electrum-GRS server when using testnet, that server will need to running on testnet configuration as well.

When not explicitly configured using the command line argument above, Sparrow stores its mainnet config file, log file and wallets in a home folder location appropriate to the operating system:

Platform | Location
-------- | --------
OSX      | ~/.sparrow-grs
Linux    | ~/.sparrow-grs
Windows  | %APPDATA%/Sparrow-GRS

Testnet and regtest configurations (along with their wallets) are stored in subfolders to allow easy switching between networks.

## Reporting Issues

Please use the [Issues](https://github.com/Groestlcoin/sparrow/issues) tab above to report an issue. If possible, look in the sparrow-grs.log file in the configuration directory for information helpful in debugging.

## License

Sparrow-GRS is licensed under the Apache 2 software licence.

## Credit

![Yourkit](https://www.yourkit.com/images/yklogo.png)

Sparrow Groestlcoin Wallet uses the [Yourkit Java Profiler](https://www.yourkit.com/java/profiler/) to profile and improve performance.
YourKit supports open source projects with useful tools for monitoring and profiling Java and .NET applications.

This project is forked from: https://github.com/sparrowwallet/sparrow/
