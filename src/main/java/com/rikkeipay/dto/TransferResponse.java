package com.rikkeipay.dto;

public record TransferResponse(
    String transactionId,
    String status, // SUCCESS or FAILED
    String message
) {
}
