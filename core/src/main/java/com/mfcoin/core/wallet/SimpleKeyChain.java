package com.mfcoin.core.wallet;

import com.mfcoin.core.protos.Protos;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.wallet.BasicKeyChain;

import com.mfcoin.core.util.KeyUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;

/**
 * @author John L. Jegutanis
 */
public class SimpleKeyChain extends BasicKeyChain {

    public SimpleKeyChain(KeyCrypter crypter) {
        super(crypter);
    }

    public SimpleKeyChain() {
        super();
    }

    Map<ECKey, Protos.Key.Builder> toEditableProtobufs() {
        Map<ECKey, Protos.Key.Builder> result = new LinkedHashMap<ECKey, Protos.Key.Builder>();
        for (ECKey ecKey : getKeys()) {
            Protos.Key.Builder protoKey = KeyUtils.serializeKey(ecKey);
            result.put(ecKey, protoKey);
        }
        return result;
    }
}
