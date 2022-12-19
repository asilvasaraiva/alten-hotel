package com.api.alten.hotel.exceptions;

/**
 * Class exception thrown when the range of dates informed is more than 30 days ahead.
 * @author Alexsandro Saraiva
 */
public class LimitOfDaysException extends CustomException{
    public LimitOfDaysException(String message) {
        super("The reservation can not be made more than thirty days in advance");
    }
}
