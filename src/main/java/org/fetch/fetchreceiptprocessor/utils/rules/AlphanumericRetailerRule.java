package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

public class AlphanumericRetailerRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        return (int) receiptDTO.getRetailer().chars().filter(Character::isLetterOrDigit).count();
    }
}
