package org.fetch.fetchreceiptprocessor.utils.rules;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

public interface ReceiptPointRule {
    int calculatePoints(ReceiptDTO receiptDTO);
}
