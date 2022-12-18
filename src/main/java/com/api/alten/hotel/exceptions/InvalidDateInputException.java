package com.api.alten.hotel.exceptions;

public class InvalidDateInputException extends CustomException{
    public InvalidDateInputException() {
        super("The informed date is invalid, please use the correct format YYYY-MM-DD ");
    }
}
