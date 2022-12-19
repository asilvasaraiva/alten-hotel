package com.api.alten.hotel.exceptions;

/**
 * Abstract Class used as base to other exceptions handled in the API.
 * @author Alexsandro Saraiva
 */
public abstract class CustomException extends RuntimeException{

    protected CustomException(final String message){
        super(message);
    }

}
