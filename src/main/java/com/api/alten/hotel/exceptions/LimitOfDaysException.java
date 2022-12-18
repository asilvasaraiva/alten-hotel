package com.api.alten.hotel.exceptions;

public class LimitOfDaysException extends CustomException{
    public LimitOfDaysException(String message) {
        super("The reservation can not be made more than thirty days in advance");
    }
}
