package com.api.alten.hotel.exceptions;

/**
 * Class exception thrown when the date informed is not valid.
 * @author Alexsandro Saraiva
 */
public class InvalidDateInputException extends CustomException{
    public InvalidDateInputException() {
        super("The informed date is invalid, please use the correct format YYYY-MM-DD ");
    }
}
