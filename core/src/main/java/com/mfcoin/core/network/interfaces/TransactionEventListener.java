package com.mfcoin.core.network.interfaces;

import com.mfcoin.core.network.ScriptStatus;
import com.mfcoin.core.network.BlockHeader;
import com.mfcoin.core.network.ServerClient.HistoryTx;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface TransactionEventListener<T> {
    void onNewBlock(BlockHeader header);

    void onBlockUpdate(BlockHeader header);

    void onScriptStatusUpdate(ScriptStatus status);

    void onTransactionHistory(ScriptStatus status, List<HistoryTx> historyTxes);

    void onTransactionUpdate(T transaction);

    void onTransactionBroadcast(T transaction);

    void onTransactionBroadcastError(T transaction);
}
