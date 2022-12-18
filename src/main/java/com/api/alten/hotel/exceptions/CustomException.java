package com.api.alten.hotel.exceptions;

public abstract class CustomException extends RuntimeException{

    protected CustomException(final String message){
        super(message);
    }

}
