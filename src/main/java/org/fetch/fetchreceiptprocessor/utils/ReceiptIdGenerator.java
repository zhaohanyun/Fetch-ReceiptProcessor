package org.fetch.fetchreceiptprocessor.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;

public class ReceiptIdGenerator {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String generateId(ReceiptDTO receiptDTO) {
        try {
            String receiptJson = objectMapper.writeValueAsString(receiptDTO);

            // Compute SHA-256 hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(receiptJson.getBytes(StandardCharsets.UTF_8));
            return bytesToUUID(hash);
        } catch (JsonProcessingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate receipt ID", e);
        }
    }

    private static String bytesToUUID(byte[] hash) {
        // Use first 16 bytes of SHA-256 hash to form UUID
        long mostSigBits = 0;
        long leastSigBits = 0;
        for (int i = 0; i < 8; i++) {
            mostSigBits = (mostSigBits << 8) | (hash[i] & 0xff);
            leastSigBits = (leastSigBits << 8) | (hash[i + 8] & 0xff);
        }
        return new UUID(mostSigBits, leastSigBits).toString();
    }
}
