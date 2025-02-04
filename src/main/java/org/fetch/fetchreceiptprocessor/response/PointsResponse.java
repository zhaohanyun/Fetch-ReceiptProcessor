package org.fetch.fetchreceiptprocessor.response;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class PointsResponse {
    @Min(value = 0)
    private final int points;
}