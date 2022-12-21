package com.api.alten.hotel.controller;


import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.room.service.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


/**
 * Class responsible for receive, delegate and answer back a request related to the Room management.
 * @author Alexsandro Saraiva
 */
@RestController
@RequestMapping("/room")
@Api(value = "API entrypoint to verify the availability of the room",tags = "Room Management")
public class RoomController {

    @Autowired
    private RoomService roomService;

    /**
     * Endpoint method contained in the RoomController class that verifies if a room is available in
     * the informed range of days.
     * @param checkIn A string that contains the check-in desirable date.
     * @param checkOut A string that contains the check-out desirable date.
     * @return A string informing if the provided period is available to be booked or the days already reserved in the
     * same interval.
     */
    @ApiOperation(value = "Verify in the API System if in a range of dates the room is available to be booked",
            response = Boolean.class
    )
    @GetMapping(value = "/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> checkAvailable(@RequestParam("checkIn")  String checkIn,
                                                  @RequestParam("checkOut") String checkOut){
        return ResponseEntity.ok(roomService.checkAvailability(checkIn,checkOut));
    }


}
