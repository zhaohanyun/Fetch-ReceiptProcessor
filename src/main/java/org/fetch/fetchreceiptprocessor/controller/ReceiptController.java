package org.fetch.fetchreceiptprocessor.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.fetch.fetchreceiptprocessor.payload.ReceiptDTO;
import org.fetch.fetchreceiptprocessor.response.NotFoundResponse;
import org.fetch.fetchreceiptprocessor.response.PointsResponse;
import org.fetch.fetchreceiptprocessor.response.ProcessReceiptResponse;
import org.fetch.fetchreceiptprocessor.service.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/receipts")
@Slf4j
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ProcessReceiptResponse> processReceipt(@Valid @RequestBody ReceiptDTO receiptDTO) {
        log.info("Received request to process receipt.");
        String receiptId = receiptService.processReceipt(receiptDTO);
        log.info("Processed receipt with ID: {}", receiptId);
        return ResponseEntity.ok(new ProcessReceiptResponse(receiptId));
        // bad request are handled by GlobalExceptionHandler
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<?> getReceiptPoints(@PathVariable String id) {
        log.info("Received request to retrieve points for receipt ID: {}", id);
        Integer points = receiptService.getPointsById(id);
        if (points != null) {
            log.info("Points for receipt ID {}: {}", id, points);
            return ResponseEntity.ok(new PointsResponse(points));
        }
        log.warn("Receipt ID {} not found", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundResponse());
    }
}
