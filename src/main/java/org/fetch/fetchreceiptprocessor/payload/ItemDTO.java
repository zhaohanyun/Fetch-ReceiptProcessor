package org.fetch.fetchreceiptprocessor.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class ItemDTO {
    @NotBlank
    @Pattern(regexp = "^[\\w\\s\\-]+$")
    private String shortDescription;

    @NotBlank
    @Pattern(regexp = "^\\d+\\.\\d{2}$") // Matches xx.xx
    private String price;
}