package com.mfcoin.core.wallet.families.bitcoin;

import com.mfcoin.core.network.ScriptStatus;
import com.mfcoin.core.network.interfaces.BlockchainConnection;

/**
 * @author John L. Jegutanis
 */
public interface BitBlockchainConnection extends BlockchainConnection<BitTransaction> {
    void getUnspentTx(ScriptStatus status, BitTransactionEventListener listener);
}
