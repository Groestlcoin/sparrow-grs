# Sparrow-GRS Groestlcoin Wallet

Sparrow-GRS is a modern desktop Groestlcoin wallet application supporting most hardware wallets and built on common standards such as PSBT, with an emphasis on transparency and usability.

More information (and release binaries) can be found at https://groestlcoin.org. Release binaries are also available directly from [Github](https://github.com/Groestlcoin/sparrow/releases).

## Building

To clone this project, use

`git clone --recursive https://github.com/Groestlcoin/sparrow`

or for those without SSH credentials:

`git clone --recursive https://github.com/sparrowwallet/sparrow.git`

In order to build, Sparrow-GRS requires Java 17 or higher to be installed.
The release binaries are built with [Eclipse Temurin 18.0.1+10](https://github.com/adoptium/temurin18-binaries/releases/tag/jdk-18.0.1%2B10).

Other packages may also be necessary to build depending on the platform. On Debian/Ubuntu systems:

`sudo apt install -y rpm fakeroot binutils`


The Sparrow-GRS binaries can be built from source using

`./gradlew jpackage`

Note that to build the Windows installer, you will need to install [WiX](https://github.com/wixtoolset/wix3/releases).

When updating to the latest HEAD

`git pull --recurse-submodules`

The release binaries are reproducible from v1.5.0 onwards (pre codesigning and installer packaging). More detailed [instructions on reproducing the binaries](docs/reproducible.md) are provided.

## Running

If you prefer to run Sparrow-GRS directly from source, it can be launched from within the project directory with

`./sparrow`

Java 17 or higher must be installed.

## Configuration

Sparrow-GRS has a number of command line options, for example to change its home folder or use testnet:

```
./sparrow-grs -h

Usage: sparrow-grs [options]
  Options:
    --dir, -d
      Path to Sparrow-GRS home folder
    --help, -h
      Show usage
    --level, -l
      Set log level
      Possible Values: [ERROR, WARN, INFO, DEBUG, TRACE]      
    --network, -n
      Network to use
      Possible Values: [mainnet, testnet, regtest, signet]
```

As a fallback, the network (mainnet, testnet, regtest or signet) can also be set using an environment variable `SPARROW_NETWORK`. For example:

`export SPARROW_NETWORK=testnet`

A final fallback which can be useful when running the Sparrow-GRS binary is to create a file called ``network-testnet`` in the Sparrow-GRS home folder (see below) to configure the testnet network.

Note that if you are connecting to an Electrum-GRS server when using testnet, that server will need to be running on testnet configuration as well.

When not explicitly configured using the command line argument above, Sparrow-GRS stores its mainnet config file, log file and wallets in a home folder location appropriate to the operating system:

| Platform | Location              |
|----------|-----------------------|
| OSX      | ~/.sparrow-grs        |
| Linux    | ~/.sparrow-grs        |
| Windows  | %APPDATA%/Sparrow-grs |

Testnet, regtest and signet configurations (along with their wallets) are stored in subfolders to allow easy switching between networks.

## Reporting Issues

Please use the [Issues](https://github.com/Groestlcoin/sparrow/issues) tab above to report an issue. If possible, look in the sparrow-grs.log file in the configuration directory for information helpful in debugging.

## License

Sparrow-GRS is licensed under the Apache 2 software licence.

## GPG Key

The Sparrow-GRS release binaries here are signed using [hashengineering's GPG key](https://keybase.io/hashengineering):  
Fingerprint: 35C20DAE5ECF9A893246724CA615EB0C5CEBDEDE
64-bit: A615 EB0C 5CEB DEDE

## Credit

![Yourkit](https://www.yourkit.com/images/yklogo.png)

Sparrow-GRS Groestlcoin Wallet uses the [Yourkit Java Profiler](https://www.yourkit.com/java/profiler/) to profile and improve performance.
YourKit supports open source projects with useful tools for monitoring and profiling Java and .NET applications.

This project is forked from: https://github.com/sparrowwallet/sparrow/
