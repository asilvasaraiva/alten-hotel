package com.api.alten.hotel.service;

import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import org.springframework.http.HttpStatus;

public interface BookingService {
    Reservation createBooking(ReservationRequest reservationRequest);

    HttpStatus modifyBooking(Long id, ReservationRequest reservationRequest);
}
