package com.obamax.WalletUserOnboarding.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public BadRequestException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
