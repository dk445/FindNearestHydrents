package com.bookiply.interview.error;

/**
 * Dto for returning the error object to client
 */
public class ErrorResponseDto {

    private final String errorMessage;

    public ErrorResponseDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
