package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

import java.math.BigDecimal;

public class RoundTotalRule implements ReceiptPointRule {

    @Override
    public int calculatePoints(ReceiptDTO receiptDTO) {
        BigDecimal totalAmount = new BigDecimal(receiptDTO.getTotal());
        return (totalAmount.scale() == 0 || totalAmount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) ? 50 : 0;
    }
}
