package com.mfcoin.core.wallet.families.bitcoin;

import com.mfcoin.core.network.ScriptStatus;
import com.mfcoin.core.network.ServerClient.UnspentTx;
import com.mfcoin.core.network.interfaces.TransactionEventListener;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface BitTransactionEventListener extends TransactionEventListener<BitTransaction> {
    void onUnspentTransactionUpdate(ScriptStatus status, List<UnspentTx> UnspentTxes);
}
