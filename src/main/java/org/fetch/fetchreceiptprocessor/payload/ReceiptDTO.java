package org.fetch.fetchreceiptprocessor.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ReceiptDTO {
    @NotBlank
    @Pattern(regexp = "^[\\w\\s\\-&]+$")
    private String retailer;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}") // Matches YYYY-MM-DD
    private String purchaseDate;

    @NotBlank
    @Pattern(regexp = "\\d{2}:\\d{2}") // Matches HH:mm
    private String purchaseTime;

    @Size(min = 1) @Valid
    private List<ItemDTO> items;

    @NotBlank
    @Pattern(regexp = "^\\d+\\.\\d{2}$") // Matches xx.xx
    private String total;

}
