package com.api.alten.hotel.service;

import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.reservation.entity.Reservation;

public interface BookingService {
    Reservation createBooking(ReservationRequest reservationRequest);
}
