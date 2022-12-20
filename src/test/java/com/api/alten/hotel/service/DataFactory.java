package com.api.alten.hotel.service;

import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.resources.room.entity.Room;

import java.time.LocalDate;

public class DataFactory {

    public static Reservation buildReservation(LocalDate checkIn, LocalDate checkOut){
        return Reservation.builder()
                .id(123L)
                .reservationCode(123456789L)
                .room(buildRoom())
                .checkIn(checkIn)
                .checkOut(checkOut)
                .clientName("My build Reservation")
                .build();
    }

    public static Reservation buildReservation(){
        return Reservation.builder()
                .id(123L)
                .reservationCode(123456789L)
                .room(buildRoom())
                .checkIn(buildDate(2))
                .checkOut(LocalDate.now().plusDays(1))
                .clientName("My build Reservation")
                .build();

    }

    public static Room buildRoom(){
        return Room.builder()
                .id(12L)
                .name("Room One")
                .build();

    }

    public static ReservationRequest buildReservationRequest(LocalDate checkIn, LocalDate checkOut,Long reservationCode){
        var reservationRequest = new ReservationRequest();
            reservationRequest.setCheckIn(checkIn.toString());
            reservationRequest.setCheckOut(checkOut.toString());
            reservationRequest.setReservationCode(reservationCode);
            reservationRequest.setClientName("My build Reservation Request");
            return reservationRequest;
    }
    public static ReservationRequest buildReservationRequest(){
        var reservationRequest = new ReservationRequest();
        reservationRequest.setCheckIn(buildDate(1).toString());
        reservationRequest.setCheckOut(buildDate(2).toString());
        reservationRequest.setClientName("My build Reservation Request");
        return reservationRequest;
    }

    public static LocalDate buildDate(int amount){
        return LocalDate.now().plusDays(amount);
    }
}
