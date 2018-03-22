package com.mfcoin.core.wallet.families.bitcoin;

import com.mfcoin.core.network.AddressStatus;
import com.mfcoin.core.network.interfaces.BlockchainConnection;

/**
 * @author John L. Jegutanis
 */
public interface BitBlockchainConnection extends BlockchainConnection<BitTransaction> {
    void getUnspentTx(AddressStatus status, BitTransactionEventListener listener);
}
