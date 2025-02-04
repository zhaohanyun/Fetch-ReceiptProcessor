package org.fetch.fetchreceiptprocessor.response;

import lombok.Getter;

@Getter
public class NotFoundResponse {
    private final String description = "No receipt found for that ID.";
}
