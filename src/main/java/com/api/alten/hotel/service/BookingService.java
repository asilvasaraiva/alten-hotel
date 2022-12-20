package com.api.alten.hotel.service;

import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import org.springframework.http.HttpStatus;

/**
 * Interface class that define and manage methods signatures for BookingServiceImpl.
 * @author Alexsandro Saraiva
 */
public interface BookingService {
    Reservation createBooking(ReservationRequest reservationRequest);

    HttpStatus modifyBooking(Long id, ReservationRequest reservationRequest);

    HttpStatus cancelBooking(Long reservationCode);
}
