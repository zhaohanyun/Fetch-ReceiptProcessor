package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

import java.time.LocalDate;

public class OddDayPurchaseRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        LocalDate purchaseDate = LocalDate.parse(receiptDTO.getPurchaseDate());
        return (purchaseDate.getDayOfMonth() % 2 == 1) ? 6 : 0;
    }
}

