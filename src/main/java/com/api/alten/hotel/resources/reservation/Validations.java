package com.api.alten.hotel.resources.reservation;

import com.api.alten.hotel.exceptions.InvalidCheckInDateException;
import com.api.alten.hotel.exceptions.InvalidDateInputException;
import com.api.alten.hotel.exceptions.LimitOfDaysException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
public class Validations {

    private static final int MAX_LENGTH_OF_MONTHS = 1;
    private static final int MAX_AMOUNT_OF_DAYS = 3;
    private static final int MIN_AMOUNT_OF_DAYS = 1;
    private static final int MIN_DAYS_TO_START_A_RESERVATION = 1;

    public static void preValidations(LocalDate checkIn,LocalDate checkOut){
        isValidStartingDate(checkIn);
        isValidAmountOfDays(checkIn,checkOut);
        isValidMonth(checkIn,checkOut);
        isValidYear(checkIn,checkOut);
    }

    private static void isValidMonth(LocalDate checkIn, LocalDate checkOut){
        if( !(Period.between(checkIn, checkOut).getMonths() <= MAX_LENGTH_OF_MONTHS)){
            throw new LimitOfDaysException("The reservation can not be made more than"+ MAX_LENGTH_OF_MONTHS+ "month ahead");
        }
    }

    private static void isValidYear(LocalDate checkIn,LocalDate checkOut){
        if(!(checkIn.getYear() == LocalDate.now().getYear() && checkOut.getYear() == LocalDate.now().getYear())){
            throw new LimitOfDaysException("The reservation can not be made more than"+ MAX_LENGTH_OF_MONTHS+ "month ahead");
        }
    }

    private static void isValidAmountOfDays(LocalDate checkIn, LocalDate checkOut) {
        var daysBetween = Period.between(checkIn, checkOut).getDays();

        if( daysBetween <= 0){
            throw new InvalidCheckInDateException("The minimum length of day per reservation is "+MIN_AMOUNT_OF_DAYS+ " day.");
        }

        if(!(daysBetween <= MAX_AMOUNT_OF_DAYS)){
            throw new InvalidCheckInDateException("The maximum length of days per reservation is "+MAX_AMOUNT_OF_DAYS+ " days.");
        }
    }

    private static void isValidStartingDate(LocalDate checkIn){
        if(!(Period.between(LocalDate.now(), checkIn).getDays() >= MIN_DAYS_TO_START_A_RESERVATION)){
            throw new InvalidCheckInDateException("The day selected for check-in must be superior than today's date.");
        }
    }

    public static LocalDate customParseDate(String date){
        try{
            return LocalDate.parse(date);
        }catch (Exception e){
            throw new InvalidDateInputException();
        }
    }

}

