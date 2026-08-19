package com.rikkeipay.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransferRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Nên CHẶN request khi thiếu thông tin hoặc sai định dạng")
    public void testInvalidTransferRequest() {
        // Giả lập request chuyển khoản mang dữ liệu lỗi
        TransferRequest invalidRequest = new TransferRequest(
                "USER_001",
                "",                        // Lỗi: Để trống số tài khoản nhận
                "XYZ",                     // Lỗi: Mã ngân hàng không nằm trong danh sách
                new BigDecimal("5000"),    // Lỗi: Số tiền nhỏ hơn mức tối thiểu 10,000 VND
                "Chuyển tiền test"
        );

        // Chạy Validation
        Set<ConstraintViolation<TransferRequest>> violations = validator.validate(invalidRequest);

        // In log ra console để làm minh chứng
        System.out.println("--- LOG KIỂM THỬ REQUEST LỖI ---");
        for (ConstraintViolation<TransferRequest> violation : violations) {
            System.out.println("❌ Field '" + violation.getPropertyPath() + "' bị lỗi: " + violation.getMessage());
        }

        // Kiểm tra xem hệ thống có bắt được chính xác 3 lỗi không
        assertFalse(violations.isEmpty(), "Hệ thống phải bắt được lỗi validation");
        assertEquals(3, violations.size(), "Phải có đúng 3 lỗi bị bắt");
    }

    @Test
    @DisplayName("Nên CHO PHÉP request nếu dữ liệu hợp lệ")
    public void testValidTransferRequest() {
        // Giả lập request chuyển khoản hợp lệ
        TransferRequest validRequest = new TransferRequest(
                "USER_001",
                "1903456789011",
                "VCB",
                new BigDecimal("50000"),
                "Thanh toán dịch vụ"
        );

        // Chạy Validation
        Set<ConstraintViolation<TransferRequest>> violations = validator.validate(validRequest);

        // In log
        System.out.println("--- LOG KIỂM THỬ REQUEST HỢP LỆ ---");
        if (violations.isEmpty()) {
            System.out.println("✅ Request dữ liệu sạch, an toàn để chuyển xuống Core Banking.");
        }

        // Kiểm tra xem danh sách lỗi có trống không
        assertTrue(violations.isEmpty(), "Không được có lỗi nào khi request hợp lệ");
    }
}
