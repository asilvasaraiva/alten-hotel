package com.api.alten.hotel.controller;


import com.api.alten.hotel.resources.reservation.ReservationRequest;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@Api(value = "API entrypoint to create, modify and cancel a reservation.",tags = "Reservation Management")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @ApiOperation(value = "Endpoint to create a new reservation in the API System with Name, check-in and check-out date.",
            response = Reservation.class,
            notes = "Dates must be valid  and in the format YYYY-MM-DD"
    )
    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(bookingService.createBooking(reservationRequest));
    }

    @ApiOperation(value = "Endpoint to modify an existent reservation in the API System with reservation code provided in the create step",
            response = HttpStatus.class,
            notes = "Reservation code is a number and must exist"
    )
    @PatchMapping(value= "/{reservationCode}/modify",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> modifyReservation(@PathVariable("reservationCode") Long reservationCode, @RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(bookingService.modifyBooking(reservationCode,reservationRequest));
    }

    @ApiOperation(value = "Endpoint to delete an existent reservation in the API System with reservation code provided in the create step",
            response = HttpStatus.class,
            notes = "Reservation code is a number and must exist"
    )
    @DeleteMapping(value="/{reservationCode}/cancel",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus>  cancelReservation(@PathVariable("reservationCode") Long reservationCode){
        return ResponseEntity.ok(bookingService.cancelBooking(reservationCode));
    }
}
