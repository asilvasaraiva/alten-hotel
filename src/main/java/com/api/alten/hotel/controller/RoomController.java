package com.api.alten.hotel.controller;


import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.room.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
@Api(value = "API entrypoint to verify the availability of the room",tags = "Room Management")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @ApiOperation(value = "Verify in the API System if a range of dates is available to book",
            response = Boolean.class
    )
    @GetMapping(value = "/available", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> checkAvailable(@RequestBody ReservationRequest reservationRequest){
        return ResponseEntity.ok(roomService.checkAvailability(reservationRequest));
    }


}
