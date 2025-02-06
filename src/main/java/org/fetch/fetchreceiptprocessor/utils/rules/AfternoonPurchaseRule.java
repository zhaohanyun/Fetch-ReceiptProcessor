package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AfternoonPurchaseRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        LocalTime purchaseTime = LocalTime.parse(receiptDTO.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));
        return (purchaseTime.isAfter(LocalTime.of(14, 0)) && purchaseTime.isBefore(LocalTime.of(16, 0))) ? 10 : 0;
    }
}

