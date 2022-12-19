package com.api.alten.hotel.exceptions;

public class NotFoundException extends CustomException{
    public NotFoundException() {
        super("Resource not Found");
    }
}
