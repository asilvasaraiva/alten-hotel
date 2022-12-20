package com.api.alten.hotel.resources.reservation;

import com.api.alten.hotel.exceptions.InvalidCheckInDateException;
import com.api.alten.hotel.exceptions.InvalidDateInputException;
import com.api.alten.hotel.exceptions.LimitOfDaysException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class that validates the input dates provided defined in ReservationRequest class.
 * @author Alexsandro Saraiva
 */
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

    /**
     * Utility method implementation for preValidations function that validate if the period os days
     * informed in check-in/check-out input date is more than a month length.
     */
    private static void isValidMonth(LocalDate checkIn, LocalDate checkOut){
        if( !(Period.between(checkIn, checkOut).getMonths() <= MAX_LENGTH_OF_MONTHS)){
            throw new LimitOfDaysException("The reservation can not be made more than"+ MAX_LENGTH_OF_MONTHS+ "month ahead");
        }
    }

    /**
     * Utility method implementation for preValidations function that validate if the period os days
     * informed in check-in/check-out input date is in the same year as today's.
     */
    private static void isValidYear(LocalDate checkIn,LocalDate checkOut){
        if(!(checkIn.getYear() == LocalDate.now().getYear() && checkOut.getYear() == LocalDate.now().getYear())){
            throw new LimitOfDaysException("The reservation can not be made more than"+ MAX_LENGTH_OF_MONTHS+ "month ahead");
        }
    }

    /**
     * Utility method implementation for preValidations function that validate if the period os days informed in
     * check-in/check-out input date is equal or less than 3 days.
     */
    private static void isValidAmountOfDays(LocalDate checkIn, LocalDate checkOut) {
        var daysBetween = Period.between(checkIn, checkOut).getDays();

        if( daysBetween <= 0){
            throw new InvalidCheckInDateException("The minimum number of days per reservation is "+MIN_AMOUNT_OF_DAYS);
        }

        if(!(daysBetween <= MAX_AMOUNT_OF_DAYS)){
            throw new InvalidCheckInDateException("The maximum number of days per reservation is "+MAX_AMOUNT_OF_DAYS+ " days.");
        }
    }

    /**
     * Utility method implementation for preValidations function that validate if the day informed in check-in input
     *  date is at least one day ahead from today's date.
     */
    private static void isValidStartingDate(LocalDate checkIn){
        if(!(Period.between(LocalDate.now(), checkIn).getDays() >= MIN_DAYS_TO_START_A_RESERVATION)){
            throw new InvalidCheckInDateException("The day selected for check-in must be superior than today's date.");
        }
    }

    /**
     * Utility method implementation function that validate if the day informed in check-in input is a valid entry.
     */
    public static LocalDate customParseDate(String date){
        try{
            return LocalDate.parse(date);
        }catch (Exception e){
            throw new InvalidDateInputException();
        }
    }

}

