package com.api.alten.hotel.service;

import com.api.alten.hotel.resources.dateTable.service.DateTableService;
import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.resources.reservation.service.ReservationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.api.alten.hotel.resources.reservation.Validations.*;

/**
 * Class that implements the methods signatures defined in BookingService.
 * @author Alexsandro Saraiva
 */

@Service
@Slf4j
public class BookingServiceImpl implements BookingService{
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private DateTableService dateTableService;

    /**
     * The implementation of a method that works as start point to create a complete reservation. The full object with
     * its is ID and reservation code is a booking.
     * @param reservationRequest Object that contains within the client name, check-in and check-out dates.
     * @return Reservation object created if successful.
     */

    public Reservation createBooking(ReservationRequest reservationRequest){
        var checkIn = customParseDate(reservationRequest.getCheckIn());
        var checkOut = customParseDate(reservationRequest.getCheckOut());

        preValidations(checkIn,checkOut);

        return reservationService.createReservation(reservationRequest.getClientName(),checkIn,checkOut);
    }

    /**
     * The implementation of a method that works as start point to modify a Booking using the reservation code of
     * the booking
     * @param reservationCode Long reservation code.
     * @param reservationRequest Object that contains within the client name, check-in and check-out dates.
     * @return HttpStatus with the status.
     */
    @Override
    public HttpStatus modifyBooking(Long reservationCode, ReservationRequest reservationRequest) {
        var newCheckIn = customParseDate(reservationRequest.getCheckIn());
        var newCheckOut = customParseDate(reservationRequest.getCheckOut());

        preValidations(newCheckIn,newCheckOut);

        return reservationService.modifyReservation(reservationCode,newCheckIn,newCheckOut);
    }

    /**
     * The implementation of a method that works as start point to delete an existent reservation using
     * the reservation code of the booking.
     * @param reservationCode Long reservation code.
     * @return HttpStatus with the status.
     */
    @Override
    public HttpStatus cancelBooking(Long reservationCode) {
        return reservationService.cancelReservation(reservationCode);
    }


}
