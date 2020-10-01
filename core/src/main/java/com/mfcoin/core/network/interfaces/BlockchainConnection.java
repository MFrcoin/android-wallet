package com.mfcoin.core.network.interfaces;

import com.mfcoin.core.network.ScriptStatus;

import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.script.Script;

import java.util.List;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 */
public interface BlockchainConnection<T> {
    void getBlock(int height, TransactionEventListener<T> listener);

    void subscribeToBlockchain(final TransactionEventListener<T> listener);

    void subscribeToScripts(List<Script> scripts,
                            TransactionEventListener<T> listener);

    void getHistoryTx(ScriptStatus status, TransactionEventListener<T> listener);

    void getTransaction(Sha256Hash txHash, TransactionEventListener<T> listener);

    void broadcastTx(final T tx, final TransactionEventListener<T> listener);

    boolean broadcastTxSync(final T tx);

    void ping(@Nullable String versionString);

    void addEventListener(ConnectionEventListener listener);

    void resetConnection();

    void stopAsync();

    boolean isActivelyConnected();

    void startAsync();
}
