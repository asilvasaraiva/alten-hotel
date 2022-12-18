package com.api.alten.hotel.controller;


import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.room.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/available")
    public ResponseEntity<Boolean> checkAvailable(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(roomService.checkAvailability(reservationRequest));
    }


}
