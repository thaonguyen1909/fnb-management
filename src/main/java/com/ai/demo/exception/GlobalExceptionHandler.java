package com.ai.demo.exception;

import ch.qos.logback.core.boolex.EvaluationException;
import com.ai.demo.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //Hung loi nghiep vu
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Void>> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    //Hung loi validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handlingValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldError() != null ? e.getFieldError().getDefaultMessage() : ErrorCode.INVALID_DATA.getMessage();

        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(ErrorCode.INVALID_DATA.getCode())
                .message(errorMessage)
                .build();

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Void>> handlingRunTimeException(Exception e){
        log.error("Unexpected error: ", e);
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .build();
        return ResponseEntity.status(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode()).body(apiResponse);
    }
}
