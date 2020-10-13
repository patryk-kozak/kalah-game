package com.softwaremind.pakz.kalah.api;

public class ApiError {

    private final String errorMessage;

    public ApiError(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
