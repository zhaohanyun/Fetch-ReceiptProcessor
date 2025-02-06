package org.fetch.fetchreceiptprocessor.utils;

import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;
import org.fetch.fetchreceiptprocessor.utils.rules.*;

import java.util.ArrayList;
import java.util.List;

public class ReceiptPointsCalculator {

    private final List<ReceiptPointRule> rules = new ArrayList<>();

    public ReceiptPointsCalculator() {
        // Register all rules
        rules.add(new AlphanumericRetailerRule());
        rules.add(new RoundTotalRule());
        rules.add(new MultipleOfQuarterRule());
        rules.add(new ItemPairRule());
        rules.add(new ItemDescriptionRule());
        rules.add(new OddDayPurchaseRule());
        rules.add(new AfternoonPurchaseRule());
    }

    public int calculatePoints(ReceiptDTO receiptDTO) {
        return rules.stream()
                .mapToInt(rule -> rule.calculatePoints(receiptDTO))
                .sum();
    }
}
