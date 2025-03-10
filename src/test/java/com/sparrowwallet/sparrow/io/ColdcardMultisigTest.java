package com.sparrowwallet.sparrow.io;

import com.google.common.io.ByteStreams;
import com.sparrowwallet.drongo.ExtendedKey;
import com.sparrowwallet.drongo.Network;
import com.sparrowwallet.drongo.policy.PolicyType;
import com.sparrowwallet.drongo.protocol.ScriptType;
import com.sparrowwallet.drongo.wallet.Keystore;
import com.sparrowwallet.drongo.wallet.Wallet;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Locale;

public class ColdcardMultisigTest extends IoTest {
    @Test
    public void importKeystore1() throws ImportException {
        Network.set(Network.TESTNET);
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Keystore keystore = ccMultisig.getKeystore(ScriptType.P2SH_P2WSH, getInputStream("cc-multisig-keystore-1.json"), null);
        Assert.assertEquals("Coldcard", keystore.getLabel());
        Assert.assertEquals("m/48'/1'/0'/1'", keystore.getKeyDerivation().getDerivationPath());
        Assert.assertEquals("0f056943", keystore.getKeyDerivation().getMasterFingerprint());
        Assert.assertEquals(ExtendedKey.fromDescriptor("Upub5T4XUooQzDXL58NCHk8ZCw9BsRSLCtnyHeZEExAq1XdnBFXiXVrHFuvvmh3TnCR7XmKHxkwqdACv68z7QKT1vwru9L1SZSsw8B2fuBvtSa6"), keystore.getExtendedPublicKey());
        Assert.assertTrue(keystore.isValid());
    }

    @Test(expected = ImportException.class)
    public void importKeystore1IncorrectScriptType() throws ImportException {
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Keystore keystore = ccMultisig.getKeystore(ScriptType.P2SH_P2WPKH, getInputStream("cc-multisig-keystore-1.json"), null);
    }

    @Test
    public void importKeystore2() throws ImportException {
        Network.set(Network.TESTNET);
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Keystore keystore = ccMultisig.getKeystore(ScriptType.P2SH, getInputStream("cc-multisig-keystore-2.json"), null);
        Assert.assertEquals("Coldcard", keystore.getLabel());
        Assert.assertEquals("m/45'", keystore.getKeyDerivation().getDerivationPath());
        Assert.assertEquals("6ba6cfd0", keystore.getKeyDerivation().getMasterFingerprint());
        Assert.assertEquals(ExtendedKey.fromDescriptor("tpubD9429UXFGCTKJ9NdiNK4rC5ygqSUkginycYHccqSg5gkmyQ7PZRHNjk99M6a6Y3NY8ctEUUJvCu6iCCui8Ju3xrHRu3Ez1CKB4ZFoRZDdP9"), keystore.getExtendedPublicKey());
        Assert.assertTrue(keystore.isValid());
    }

    @Test
    public void importKeystore2b() throws ImportException {
        Network.set(Network.TESTNET);
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Keystore keystore = ccMultisig.getKeystore(ScriptType.P2WSH, getInputStream("cc-multisig-keystore-2.json"), null);
        Assert.assertEquals("Coldcard", keystore.getLabel());
        Assert.assertEquals("m/48'/1'/0'/2'", keystore.getKeyDerivation().getDerivationPath());
        Assert.assertEquals("6ba6cfd0", keystore.getKeyDerivation().getMasterFingerprint());
        Assert.assertEquals(ExtendedKey.fromDescriptor("Vpub5nUnvPehg1VYQh13dGznx1P9moac3SNUrn3qhU9r85RhXabYbSSBNsNNwyR7akozAZJw1SZmRRjry1zY8PWMuw8Ga1vQZ5qzPjKyTDQwtzs"), keystore.getExtendedPublicKey());
        Assert.assertTrue(keystore.isValid());
    }

    @Test
    public void importWallet1() throws ImportException {
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Wallet wallet = ccMultisig.importWallet(getInputStream("cc-multisig-export-1.txt"), null);
        Assert.assertEquals("CC-2-of-4", wallet.getName());
        Assert.assertEquals(PolicyType.MULTI, wallet.getPolicyType());
        Assert.assertEquals(ScriptType.P2WSH, wallet.getScriptType());
        Assert.assertEquals(2, wallet.getDefaultPolicy().getNumSignaturesRequired());
        Assert.assertEquals("wsh(sortedmulti(2,coldcard1,coldcard2,coldcard3,coldcard4))", wallet.getDefaultPolicy().getMiniscript().getScript().toLowerCase(Locale.ROOT));
        Assert.assertTrue(wallet.isValid());
    }

    @Test
    public void importWallet2() throws ImportException {
        Network.set(Network.TESTNET);
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Wallet wallet = ccMultisig.importWallet(getInputStream("cc-multisig-export-2.txt"), null);
        Assert.assertEquals("CC-2-of-4", wallet.getName());
        Assert.assertEquals(PolicyType.MULTI, wallet.getPolicyType());
        Assert.assertEquals(ScriptType.P2SH_P2WSH, wallet.getScriptType());
        Assert.assertEquals(2, wallet.getDefaultPolicy().getNumSignaturesRequired());
        Assert.assertEquals("sh(wsh(sortedmulti(2,coldcard1,coldcard2,coldcard3,coldcard4)))", wallet.getDefaultPolicy().getMiniscript().getScript().toLowerCase(Locale.ROOT));
        Assert.assertTrue(wallet.isValid());
    }

    @Test
    public void importWalletMultiDeriv() throws ImportException {
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        Wallet wallet = ccMultisig.importWallet(getInputStream("cc-multisig-export-multideriv.txt"), null);
        Assert.assertEquals("el-CC-3-of-3-sb-2", wallet.getName());
        Assert.assertEquals(PolicyType.MULTI, wallet.getPolicyType());
        Assert.assertEquals(ScriptType.P2WSH, wallet.getScriptType());
        Assert.assertEquals(3, wallet.getDefaultPolicy().getNumSignaturesRequired());
        Assert.assertEquals("wsh(sortedmulti(3,coldcard1,coldcard2,coldcard3))", wallet.getDefaultPolicy().getMiniscript().getScript().toLowerCase(Locale.ROOT));
        Assert.assertEquals("06b57041", wallet.getKeystores().get(0).getKeyDerivation().getMasterFingerprint());
        Assert.assertEquals("m/48'/0'/0'/2'", wallet.getKeystores().get(0).getKeyDerivation().getDerivationPath());
        Assert.assertEquals("xpub6EfEGa5isJbQFSswM5Uptw5BSq2Td1ZDJr3QUNUcMySpC7itZ3ccypVHtLPnvMzKQ2qxrAgH49vhVxRcaQLFbixAVRR8RACrYTp88Uv9h8Z", wallet.getKeystores().get(0).getExtendedPublicKey().toString());
        Assert.assertEquals("ca9a2b19", wallet.getKeystores().get(2).getKeyDerivation().getMasterFingerprint());
        Assert.assertEquals("m/47'/0'/0'/1'", wallet.getKeystores().get(2).getKeyDerivation().getDerivationPath());
        Assert.assertTrue(wallet.isValid());
    }

    @Test
    public void exportWallet1() throws ImportException, ExportException, IOException {
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        byte[] walletBytes = ByteStreams.toByteArray(getInputStream("cc-multisig-export-1.txt"));
        Wallet wallet = ccMultisig.importWallet(new ByteArrayInputStream(walletBytes), null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ccMultisig.exportWallet(wallet, baos);
        byte[] exportedBytes = baos.toByteArray();
        String original = new String(walletBytes);
        String exported = new String(exportedBytes);
        Assert.assertEquals(original.replaceAll("created on [0-9A-F]+", ""), exported.replace("created by Sparrow-GRS", ""));
    }

    @Test
    public void exportWalletMultiDeriv() throws ImportException, ExportException, IOException {
        ColdcardMultisig ccMultisig = new ColdcardMultisig();
        byte[] walletBytes = ByteStreams.toByteArray(getInputStream("cc-multisig-export-multideriv.txt"));
        Wallet wallet = ccMultisig.importWallet(new ByteArrayInputStream(walletBytes), null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ccMultisig.exportWallet(wallet, baos);
        byte[] exportedBytes = baos.toByteArray();
        String original = new String(walletBytes);
        String exported = new String(exportedBytes);
        Assert.assertEquals(original.replaceAll("Exported from Electrum", ""), exported.replace("Coldcard Multisig setup file (created by Sparrow)\n#", ""));
    }

    @After
    public void tearDown() throws Exception {
        Network.set(null);
    }
}
