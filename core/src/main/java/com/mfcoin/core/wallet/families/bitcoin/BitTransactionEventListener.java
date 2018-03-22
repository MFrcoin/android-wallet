package com.mfcoin.core.wallet.families.bitcoin;

import com.mfcoin.core.network.AddressStatus;
import com.mfcoin.core.network.ServerClient.UnspentTx;
import com.mfcoin.core.network.interfaces.TransactionEventListener;

import java.util.List;

/**
 * @author John L. Jegutanis
 */
public interface BitTransactionEventListener extends TransactionEventListener<BitTransaction> {
    void onUnspentTransactionUpdate(AddressStatus status, List<UnspentTx> UnspentTxes);
}
