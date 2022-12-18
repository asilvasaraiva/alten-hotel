package com.api.alten.hotel.controller;


import com.api.alten.hotel.resources.reservation.ReservationRequest;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> create(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(bookingService.createBooking(reservationRequest));
    }


}
