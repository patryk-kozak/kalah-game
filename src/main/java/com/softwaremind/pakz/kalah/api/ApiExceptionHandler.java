package com.softwaremind.pakz.kalah.api;

import com.softwaremind.pakz.kalah.IllegalMoveException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = IllegalMoveException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiError illegalMoveError(IllegalMoveException ex) {
        return new ApiError(ex.getMessage());
    }

}
