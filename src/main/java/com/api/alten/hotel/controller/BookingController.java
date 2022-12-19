package com.api.alten.hotel.controller;


import com.api.alten.hotel.resources.reservation.ReservationRequest;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(bookingService.createBooking(reservationRequest));
    }

    @PatchMapping("/{reservationCode}/modify")
    public ResponseEntity<HttpStatus> modifyReservation(@PathVariable("reservationCode") Long reservationCode, @RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(bookingService.modifyBooking(reservationCode,reservationRequest));
    }

    @DeleteMapping("/{reservationCode}/cancel")
    public ResponseEntity<HttpStatus>  cancelReservation(@PathVariable("reservationCode") Long reservationCode){
        return ResponseEntity.ok(bookingService.cancelBooking(reservationCode));
    }
}
