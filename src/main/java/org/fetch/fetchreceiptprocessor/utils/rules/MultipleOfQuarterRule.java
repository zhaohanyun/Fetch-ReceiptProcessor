package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

import java.math.BigDecimal;

public class MultipleOfQuarterRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        BigDecimal totalAmount = new BigDecimal(receiptDTO.getTotal());
        return totalAmount.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0 ? 25 : 0;
    }
}

