package com.rikkeipay.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record TransferRequest(
    @NotBlank(message = "Sender account ID must not be blank")
    String senderAccountId,

    @NotBlank(message = "Receiver account number must not be blank")
    String receiverAccountNumber,

    @NotBlank(message = "Bank code must not be blank")
    @Pattern(regexp = "^(VCB|TCB|MB|ACB|VPB)$", message = "Bank code must be valid (e.g., VCB, TCB, MB, ACB, VPB)")
    String bankCode,

    @NotNull(message = "Amount must not be null")
    @DecimalMin(value = "10000.0", message = "Amount must be greater than or equal to 10000 VND")
    BigDecimal amount,

    @Size(max = 255, message = "Description must not exceed 255 characters")
    String description
) {
}
