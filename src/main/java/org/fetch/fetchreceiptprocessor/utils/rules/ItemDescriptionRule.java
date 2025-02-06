package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ItemDTO;
import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

import java.math.BigDecimal;

public class ItemDescriptionRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        int points = 0;
        for (ItemDTO item : receiptDTO.getItems()) {
            int descriptionLength = item.getShortDescription().trim().length();
            if (descriptionLength % 3 == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                points += (int) Math.ceil(price.multiply(new BigDecimal("0.2")).doubleValue());
            }
        }
        return points;
    }
}

