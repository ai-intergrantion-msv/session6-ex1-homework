==================================================
   RikkeiPay Assistant - Validation Testing
==================================================

[TEST 1] Đang gửi Request CHUYỂN KHOẢN KHÔNG HỢP LỆ...
❌ KẾT QUẢ: Request bị chặn bởi Validation! Các lỗi phát hiện:
   -> Field 'amount': Số tiền chuyển khoản phải lớn hơn hoặc bằng 10,000 VND
   -> Field 'bankCode': Mã ngân hàng không hợp lệ (Ví dụ hợp lệ: VCB, TCB, MB, ACB, VPB)
   -> Field 'receiverAccountNumber': Số tài khoản người nhận không được để trống

[TEST 2] Đang gửi Request CHUYỂN KHOẢN HỢP LỆ...
✅ KẾT QUẢ: Request hợp lệ! Cho phép tiếp tục giao dịch tới Core Banking.

Process finished with exit code 0