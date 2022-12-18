package com.api.alten.hotel.resources.reservation;

import com.api.alten.hotel.exceptions.InvalidCheckInDate;
import com.api.alten.hotel.exceptions.LimitOfDaysException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

@Slf4j
public class Validations {

    private static final int MAX_LENGTH_OF_MONTHS = 1;
    private static final int MAX_AMOUNT_OF_DAYS = 3;
    private static final int MIN_DAYS_TO_START_A_RESERVATION = 1;

    public static boolean isValidMonth(LocalDate checkIn, LocalDate checkOut){
        if( Period.between(checkIn, checkOut).getMonths() <= MAX_LENGTH_OF_MONTHS){
            return true;
        }else{
            throw new LimitOfDaysException("The reservation can not be made more than"+ MAX_LENGTH_OF_MONTHS+ "month ahead");
        }
    }

    public static boolean isValidYear(LocalDate checkIn,LocalDate checkOut){
        if( checkIn.getYear() == LocalDate.now().getYear() && checkOut.getYear() == LocalDate.now().getYear()){
            return true;
        }else{
            throw new LimitOfDaysException("The reservation can not be made more than"+ MAX_LENGTH_OF_MONTHS+ "month ahead");
        }
    }

    public static boolean isValidAmountOfDays(LocalDate checkIn, LocalDate checkOut) {
        if( Period.between(checkIn, checkOut).getDays() <= MAX_AMOUNT_OF_DAYS) {
            return true;
        }else{
            throw new InvalidCheckInDate("The maximum amount of days per reservation is "+MAX_AMOUNT_OF_DAYS+ " days.");
        }
    }

    public static boolean isValidStartingDate(LocalDate checkIn){
        if(Period.between(LocalDate.now(), checkIn).getDays() >= MIN_DAYS_TO_START_A_RESERVATION){
            return true;
        }else{
            throw new InvalidCheckInDate("The day selected for check-in must be superior than today's date.");
        }
    }

}

