package org.fetch.fetchreceiptprocessor.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ProcessReceiptResponse {
    @NotBlank
    @Pattern(regexp = "^\\S+$")
    private String id;
}
