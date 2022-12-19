package com.api.alten.hotel.exceptions;

/**
 * Class exception thrown when a reservation already exists in the range of dates informed.
 * @author Alexsandro Saraiva
 */
public class UnavailableDateException extends CustomException{
    public UnavailableDateException(String message) {
        super(message);
    }
}
