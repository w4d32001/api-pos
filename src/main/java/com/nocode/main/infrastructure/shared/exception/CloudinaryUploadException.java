package com.nocode.main.infrastructure.shared.exception;

public class CloudinaryUploadException extends RuntimeException {
    public CloudinaryUploadException(String message) {
        super(message);
    }
}
