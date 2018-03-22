package com.mfcoin.core.coins.families;

import com.mfcoin.core.coins.CoinType;
import com.mfcoin.core.exceptions.AddressMalformedException;
import com.mfcoin.core.wallet.families.bitcoin.BitAddress;

/**
 * @author John L. Jegutanis
 *
 * This is the classical Bitcoin family that includes Litecoin, Dogecoin, Dash, etc
 */
public abstract class BitFamily extends CoinType {
    {
        family = Families.BITCOIN;
    }

    @Override
    public BitAddress newAddress(String addressStr) throws AddressMalformedException {
        return BitAddress.from(this, addressStr);
    }
}
