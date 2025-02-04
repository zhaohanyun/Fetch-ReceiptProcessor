package org.fetch.fetchreceiptprocessor.exception;

import lombok.extern.slf4j.Slf4j;
import org.fetch.fetchreceiptprocessor.response.BadRequestResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BadRequestResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Receipt is not valid");
        return ResponseEntity.badRequest().body(new BadRequestResponse());
    }
}
