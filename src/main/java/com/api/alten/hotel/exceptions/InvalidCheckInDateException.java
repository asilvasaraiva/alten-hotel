package com.api.alten.hotel.exceptions;

/**
 * Class exception thrown when the difference between the check-in and check-out
 * is less than 1 or more than 3 days defined by the business.
 * @author Alexsandro Saraiva
 */
public class InvalidCheckInDateException extends CustomException{
    public InvalidCheckInDateException(String message) {
        super(message);
    }
}
