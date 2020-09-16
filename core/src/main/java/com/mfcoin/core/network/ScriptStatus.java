package com.mfcoin.core.network;

import com.google.common.collect.Sets;
import com.mfcoin.core.network.ServerClient.HistoryTx;
import com.mfcoin.core.network.ServerClient.UnspentTx;

import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.script.Script;
import org.spongycastle.util.encoders.Hex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 */
final public class ScriptStatus {
    final Script script;
    @Nullable
    final String status;

    HashSet<HistoryTx> historyTransactions;
    HashSet<UnspentTx> unspentTransactions;
    HashSet<Sha256Hash> historyTxHashes = new HashSet<>();
    HashSet<Sha256Hash> unspentTxHashes = new HashSet<>();

    boolean stateMustBeApplied;
    boolean historyTxStateApplied;
    boolean unspentTxStateApplied;

    public ScriptStatus(Script script, @Nullable String status) {
        this.script = script;
        this.status = status;
    }

    public Script getScript() {
        return script;
    }

    public String getScriptHash() {
        return electrumHash(script.getProgram());
    }

    @Nullable
    public String getStatus() {
        return status;
    }

    /**
     * Queue transactions that are going to be fetched
     */
    public void queueHistoryTransactions(List<HistoryTx> txs) {
        if (historyTransactions == null) {
            historyTransactions = Sets.newHashSet(txs);
            historyTxHashes = fillTransactions(txs);
            stateMustBeApplied = true;
        }
    }

    /**
     * Queue transactions that are going to be fetched
     */
    public void queueUnspentTransactions(List<UnspentTx> txs) {
        if (unspentTransactions == null) {
            unspentTransactions = Sets.newHashSet(txs);
            unspentTxHashes = fillTransactions(txs);
            stateMustBeApplied = true;
        }
    }

    private HashSet<Sha256Hash> fillTransactions(Iterable<? extends HistoryTx> txs) {
        HashSet<Sha256Hash> transactionHashes = new HashSet<>();
        for (HistoryTx tx : txs) {
            transactionHashes.add(tx.getTxHash());
        }
        return transactionHashes;
    }

    /**
     * Return true if history transactions are queued
     */
    public boolean isHistoryTxQueued() {
        return historyTransactions != null;
    }

    /**
     * Return true if unspent transactions are queued
     */
    public boolean isUnspentTxQueued() {
        return unspentTransactions != null;
    }

    /**
     * Get queued history transactions
     */
    public Set<Sha256Hash> getHistoryTxHashes() {
        return historyTxHashes;
    }

    /**
     * Get queued unspent transactions
     */
    public Set<Sha256Hash> getUnspentTxHashes() {
        return unspentTxHashes;
    }

    /**
     * Get history transactions info
     */
    public HashSet<HistoryTx> getHistoryTxs() {
        return historyTransactions;
    }

    /**
     * Get unspent transactions info
     */
    public HashSet<UnspentTx> getUnspentTxs() {
        return unspentTransactions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScriptStatus status1 = (ScriptStatus) o;

        if (!script.equals(status1.script)) return false;
        return status != null ? status.equals(status1.status) : status1.status == null;
    }

    @Override
    public int hashCode() {
        int result = script.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "scriptHash=" + script +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean isHistoryTxStateApplied() {
        return historyTxStateApplied;
    }

    public void setHistoryTxStateApplied(boolean historyTxStateApplied) {
        this.historyTxStateApplied = historyTxStateApplied;
    }

    public boolean isUnspentTxStateApplied() {
        return unspentTxStateApplied;
    }

    public void setUnspentTxStateApplied(boolean unspentTxStateApplied) {
        this.unspentTxStateApplied = unspentTxStateApplied;
    }

    public boolean canCommitStatus() {
        return !stateMustBeApplied || historyTxStateApplied && unspentTxStateApplied;
    }

    private String electrumHash(byte[] script) {
        return Hex.toHexString(reverse(Sha256Hash.create(script).getBytes()));
    }

    private byte[] reverse(byte[] array) {
        if (array == null) {
            return array;
        }
        int i = array.length;
        int j = 0;
        byte[] result = new byte[i];
        while (j < array.length) {
            result[j] = array[i - 1];
            i--;
            j++;
        }
        return result;
    }
}
