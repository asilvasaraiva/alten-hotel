package com.api.alten.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<StandardResponseException> handleGeneralExceptions(Exception exception){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidCheckInDateException.class)
    public final ResponseEntity<StandardResponseException> handleInvalidCheckInExceptions(InvalidCheckInDateException exception){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(LimitOfDaysException.class)
    public final ResponseEntity<StandardResponseException> handleLimitOfDaysException(LimitOfDaysException exception){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public final ResponseEntity<StandardResponseException> handleNotSavedInDatabaseException(UnprocessableEntityException exception){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(InvalidDateInputException.class)
    public final ResponseEntity<StandardResponseException> handleInvalidDateInputException(InvalidDateInputException exception){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
