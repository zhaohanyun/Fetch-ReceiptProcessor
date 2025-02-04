package org.fetch.fetchreceiptprocessor.service;

import lombok.extern.slf4j.Slf4j;
import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;
import org.fetch.fetchreceiptprocessor.utils.ReceiptIdGenerator;
import org.fetch.fetchreceiptprocessor.utils.ReceiptPointsCalculator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ReceiptService {

    // storage for receipts and their points
    private final Map<String, Integer> receiptPoints = new HashMap<>();

    public String processReceipt(ReceiptDTO receiptDTO) {
        log.debug("Generating ID for receipt...");
        String id = ReceiptIdGenerator.generateId(receiptDTO);

        log.debug("Calculating points for receipt...");
        ReceiptPointsCalculator calculator = new ReceiptPointsCalculator();
        int totalPoints = calculator.calculatePoints(receiptDTO);

        receiptPoints.putIfAbsent(id, totalPoints);
        return id;
    }

    public Integer getPointsById(String id) {
        // If ID is found, return points; otherwise, return null
        log.debug("Looking up points for receipt ID: {}", id);
        return receiptPoints.get(id);
    }

}