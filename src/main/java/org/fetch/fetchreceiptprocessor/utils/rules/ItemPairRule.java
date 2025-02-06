package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

public class ItemPairRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        return (receiptDTO.getItems().size() / 2) * 5;
    }
}

