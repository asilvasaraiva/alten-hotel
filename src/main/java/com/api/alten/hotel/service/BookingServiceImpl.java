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

import static com.api.alten.hotel.resources.reservation.Validations.*;


@Service
@Slf4j
public class BookingServiceImpl implements BookingService{
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DateTableService dateTableService;

    public Reservation createBooking(ReservationRequest reservationRequest){
        var checkIn = customParseDate(reservationRequest.getCheckIn());
        var checkOut = customParseDate(reservationRequest.getCheckOut());

        preValidations(checkIn,checkOut);

        return reservationService.createReservation(reservationRequest.getClientName(),checkIn,checkOut);
    }

    private void preValidations(LocalDate checkIn,LocalDate checkOut){
        isValidStartingDate(checkIn);
        isValidAmountOfDays(checkIn,checkOut);
        isValidMonth(checkIn,checkOut);
        isValidYear(checkIn,checkOut);
    }


}
