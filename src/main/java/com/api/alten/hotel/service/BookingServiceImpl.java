package com.api.alten.hotel.service;

import com.api.alten.hotel.resources.dateTable.service.DateTableService;
import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.resources.reservation.service.ReservationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static com.api.alten.hotel.resources.reservation.Validations.isValidAmountOfDays;
import static com.api.alten.hotel.resources.reservation.Validations.isValidStartingDate;


@Service
@Slf4j
public class BookingServiceImpl implements BookingService{
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DateTableService dateTableService;

    public Reservation createBooking(ReservationRequest reservationRequest){
        var checkIn = LocalDate.parse(reservationRequest.getCheckIn());
        var checkOut = LocalDate.parse(reservationRequest.getCheckOut());
        var client = reservationRequest.getClientName();

        dateTableService.checkAvailability( checkIn.datesUntil(checkOut).collect(Collectors.toList()));

        Reservation booking = reservationService.createReservation(client,checkIn,checkOut);

        checkIn.datesUntil(checkOut).forEach(d-> dateTableService.saveDates(booking.getReservationCode(),d));

        return booking;
    }

    private boolean preValidation(LocalDate checkIn,LocalDate checkOut){
        return isValidStartingDate(checkIn) && isValidAmountOfDays(checkIn, checkOut);
    }


}
