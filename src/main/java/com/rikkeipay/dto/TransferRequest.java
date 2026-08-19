package com.rikkeipay.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record TransferRequest(
    @NotBlank(message = "ID tài khoản nguồn không được để trống")
    String senderAccountId,

    @NotBlank(message = "Số tài khoản người nhận không được để trống")
    String receiverAccountNumber,

    @NotBlank(message = "Mã ngân hàng không được để trống")
    @Pattern(regexp = "^(VCB|TCB|MB|ACB|VPB)$", message = "Mã ngân hàng không hợp lệ (Ví dụ hợp lệ: VCB, TCB, MB, ACB, VPB)")
    String bankCode,

    @NotNull(message = "Số tiền không được để trống")
    @DecimalMin(value = "10000.0", message = "Số tiền chuyển khoản phải lớn hơn hoặc bằng 10,000 VND")
    BigDecimal amount,

    @Size(max = 255, message = "Nội dung chuyển khoản không được vượt quá 255 ký tự")
    String description
) {
}
