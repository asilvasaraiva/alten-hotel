package com.api.alten.hotel.resources.reservation;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
public class Validations {

    private static final int MAX_LENGTH_OF_MONTHS = 1;
    private static final int MAX_AMOUNT_OF_DAYS = 3;
    private static final int MIN_DAYS_TO_START_A_RESERVATION = 1;


    public static boolean isValidDays(LocalDate checkIn, LocalDate checkOut){
        return !Period.between(checkIn, checkOut).isNegative();
    }

    public static boolean isValidMonth(LocalDate checkIn, LocalDate checkOut){
        return Period.between(checkIn, checkOut).getMonths() <= MAX_LENGTH_OF_MONTHS;
    }

    public static boolean isValidAmountOfDays(LocalDate checkIn, LocalDate checkOut){
        return Period.between(checkIn, checkOut).getDays() <= MAX_AMOUNT_OF_DAYS ;
    }

    public static boolean isValidStartingDate(LocalDate checkIn){
        return Period.between(LocalDate.now(), checkIn).getDays() >= MIN_DAYS_TO_START_A_RESERVATION;
    }

}

