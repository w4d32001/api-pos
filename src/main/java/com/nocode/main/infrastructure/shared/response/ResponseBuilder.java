package com.nocode.main.infrastructure.shared.response;

import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T> ResponseEntity<ApiResponse<T>> build(int status, String message, T data) {
        ApiResponse<T> response = new ApiResponse<>(status, message, data);
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return build(200, message, data);
    }

    public static ResponseEntity<ApiResponse<Void>> ok(String message) {
        return build(200, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return build(201, message, data);
    }

    public static ResponseEntity<ApiResponse<Void>> created(String message) {
        return build(201, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> deleted(String message) {
        return build(200, message, null);
    }
}
