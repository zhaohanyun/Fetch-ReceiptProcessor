package org.fetch.fetchreceiptprocessor.response;

import lombok.Getter;

@Getter
public class BadRequestResponse {
    private final String description = "The receipt is invalid.";
}
