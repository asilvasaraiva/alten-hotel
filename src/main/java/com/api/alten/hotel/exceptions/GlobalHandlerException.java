package com.api.alten.hotel.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Class that intercepts and handle exceptions in requests the API global scope.
 * @author Alexsandro Saraiva
 */

@RestControllerAdvice
public class GlobalHandlerException {

    /**
     * A handler method that intercepts and handle general exceptions returning its error message and status code as
     * standard response.
     * @param exception Exception thrown for any other exception cases.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<StandardResponseException> handleGeneralExceptions(Exception exception){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * A handler method that intercepts and handle InvalidCheckInDateException returning its error message and status code as
     * standard response.
     * @param invalidCheckInDateException Exception thrown when the difference between the check-in and check-out is
     *                                    less than 1 or more than 3 defined by the business.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(InvalidCheckInDateException.class)
    public final ResponseEntity<StandardResponseException> handleInvalidCheckInExceptions(InvalidCheckInDateException invalidCheckInDateException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                invalidCheckInDateException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * A handler method that intercepts and handle LimitOfDaysException returning its error message and status code as
     * standard response.
     * @param limitOfDaysException Exception thrown when the range of dates informed is more than 30 days ahead.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(LimitOfDaysException.class)
    public final ResponseEntity<StandardResponseException> handleLimitOfDaysException(LimitOfDaysException limitOfDaysException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                limitOfDaysException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * A handler method that intercepts and handle UnprocessableEntityException returning its error message and status code as
     * standard response.
     * @param unprocessableEntityException Exception thrown when the entity can not be saved in database.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(UnprocessableEntityException.class)
    public final ResponseEntity<StandardResponseException> handleNotSavedInDatabaseException(UnprocessableEntityException unprocessableEntityException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                unprocessableEntityException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * A handler method that intercepts and handle InvalidDateInputException returning its error message and status code as
     * standard response.
     * @param invalidDateInputException Exception thrown when the date informed is not valid.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(InvalidDateInputException.class)
    public final ResponseEntity<StandardResponseException> handleInvalidDateInputException(InvalidDateInputException invalidDateInputException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                invalidDateInputException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * A handler method that intercepts and handle UnavailableDateException returning its error message and status code as
     * standard response.
     * @param unavailableDateException Exception thrown when a reservation already exists in the range of dates informed.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(UnavailableDateException.class)
    public final ResponseEntity<StandardResponseException> handleUnavailableDateException(UnavailableDateException unavailableDateException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                unavailableDateException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * A handler method that intercepts and handle NotFoundException returning its error message and status code as
     * standard response.
     * @param notFoundException Exception thrown when some entity can not be found in database.
     * @return A ResponseEntity formatted as StandardResponseException message with its status codes and messages.
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<StandardResponseException> handleNotFoundException(NotFoundException notFoundException){
        StandardResponseException exceptionResponse = new StandardResponseException(LocalDateTime.now(),
                notFoundException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
