package com.api.alten.hotel.exceptions;

/**
 * Class exception thrown when the entity can not be saved in database.
 * @author Alexsandro Saraiva
 */
public class UnprocessableEntityException extends CustomException{
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
