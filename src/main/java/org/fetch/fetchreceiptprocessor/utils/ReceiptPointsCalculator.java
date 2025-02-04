package org.fetch.fetchreceiptprocessor.utils;

import org.fetch.fetchreceiptprocessor.payload.ItemDTO;
import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ReceiptPointsCalculator {

    public static int calculatePoints(ReceiptDTO receiptDTO) {
        int points = 0;

        // Rule 1: One point for every alphanumeric character in the retailer name
        points += countAlphanumeric(receiptDTO.getRetailer());

        // Rule 2: 50 points if the total is a round dollar amount with no cents
        BigDecimal totalAmount = new BigDecimal(receiptDTO.getTotal());
        if (totalAmount.scale() == 0 || totalAmount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0) {
            points += 50;
        }

        // Rule 3: 25 points if the total is a multiple of 0.25
        if (totalAmount.remainder(new BigDecimal("0.25")).compareTo(BigDecimal.ZERO) == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items on the receipt
        points += (receiptDTO.getItems().size() / 2) * 5;

        // Rule 5: Points based on item descriptions
        for (ItemDTO item : receiptDTO.getItems()) {
            int descriptionLength = item.getShortDescription().trim().length();
            if (descriptionLength % 3 == 0) {
                BigDecimal price = new BigDecimal(item.getPrice());
                points += (int) Math.ceil(price.multiply(new BigDecimal("0.2")).doubleValue());
            }
        }

        // Rule 6: 6 points if the purchase date day is odd
        LocalDate purchaseDate = LocalDate.parse(receiptDTO.getPurchaseDate());
        if (purchaseDate.getDayOfMonth() % 2 == 1) {
            points += 6;
        }

        // Rule 7: 10 points if the time of purchase is after 2:00pm and before 4:00pm
        LocalTime purchaseTime = LocalTime.parse(receiptDTO.getPurchaseTime(), DateTimeFormatter.ofPattern("HH:mm"));
        if (purchaseTime.isAfter(LocalTime.of(14, 0)) && purchaseTime.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        return points;
    }

    private static int countAlphanumeric(String str) {
        return (int) str.chars().filter(Character::isLetterOrDigit).count();
    }
}
