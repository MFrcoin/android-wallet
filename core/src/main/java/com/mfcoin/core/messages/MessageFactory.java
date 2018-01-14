package com.mfcoin.core.messages;

import org.bitcoinj.core.Transaction;

import javax.annotation.Nullable;

/**
 * @author John L. Jegutanis
 */
public interface MessageFactory {
    int maxMessageSizeBytes();

    boolean canHandlePublicMessages();

    boolean canHandlePrivateMessages();

    TxMessage createPublicMessage(String message);

    // TODO change to abstract transaction
    @Nullable
    TxMessage extractPublicMessage(Transaction transaction);
}
