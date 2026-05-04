package com.ai.demo.exception;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi hệ thống không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Mã khóa (Key) không hợp lệ", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "Người dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(1003, "Tài nguyên không tồn tại", HttpStatus.NOT_FOUND),
    AI_SERVICE_ERROR(1004, "Lỗi kết nối trí tuệ nhân tạo", HttpStatus.SERVICE_UNAVAILABLE),
    INVALID_ORDER_DATA(1005, "Dữ liệu đơn hàng không hợp lệ", HttpStatus.BAD_REQUEST),
    INVALID_DATA(1006, "Dữ liệu không hợp lệ", HttpStatus.BAD_REQUEST),
    NOT_BLANK(1007, "Trường dữ liệu không được để trống", HttpStatus.BAD_REQUEST),


    //---------------------CATEGORY(1100 -> 1500)--------------------
    CATEGORY_EXISTED(1101, "Danh mục đã tồn tại", HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_FOUND(1102, "Danh mục không tồn tại", HttpStatus.NOT_FOUND),
    CATEGORY_NAME_SIZE(1103, "Tên danh mục không được vượt quá 100 ký tự", HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }
}
