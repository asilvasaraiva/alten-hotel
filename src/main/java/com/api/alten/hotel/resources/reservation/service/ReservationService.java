package com.api.alten.hotel.resources.reservation.service;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public interface ReservationService {

    public void save(Reservation reservation);
    public Reservation createReservation(String client, LocalDate checkIn, LocalDate checkOut);
    public HttpStatus modifyReservation(Long id, LocalDate newCheckIn, LocalDate newCheckOut);
    public Reservation getReservationByCode(Long reservationCode);
    public HttpStatus cancelReservation(Long reservationCode);
}
