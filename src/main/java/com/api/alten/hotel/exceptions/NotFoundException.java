package com.api.alten.hotel.exceptions;

/**
 * Class exception thrown when some entity can not be found in database.
 * @author Alexsandro Saraiva
 */
public class NotFoundException extends CustomException{
    public NotFoundException() {
        super("Resource not Found");
    }
}
