package com.api.alten.hotel.resources.room.service;


import com.api.alten.hotel.exceptions.UnavailableDateException;
import com.api.alten.hotel.resources.dateTable.service.DateTableService;
import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.room.entity.Room;
import com.api.alten.hotel.resources.room.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.api.alten.hotel.resources.reservation.Validations.customParseDate;
import static com.api.alten.hotel.resources.reservation.Validations.preValidations;

/**
 * Class that implements the methods signatures defined in RoomService.
 * @author Alexsandro Saraiva
 */
@Service
@Slf4j
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DateTableService dateTableService;

    /**
     * Utility method implementation for getRoom function that generates, save in database and return a room.
     * @return A Room object.
     */
    @Transactional
    private Room create(){
        try {
            log.info("Creating new room in database");
            var room = Room.builder().name("Room One").build();
            return roomRepository.save(room);
        }catch (Exception e){
            throw new UnavailableDateException("Error to save new room in database");
        }
    }

    /**
     * The implementation of a method that retrieve a Room in database or create one if none can be found.
     * @return Room object.
     */
    @Override
    public Room getRoom() {
        Optional<Room> room = roomRepository.findById(1L);
        return room.orElseGet(this::create);
    }

    /**
     * The implementation of a method that returns true or false if a room is available to the informed period of time.
     * @return Boolean true or false.
     */
    @Override
    public boolean checkAvailability(ReservationRequest reservationRequest) {
        var checkIn = customParseDate(reservationRequest.getCheckIn());
        var checkOut = customParseDate(reservationRequest.getCheckOut());
        preValidations(checkIn,checkOut);
        var occurrences = dateTableService.findOccurrences(checkIn,checkOut);
        return occurrences.size() == 0;
    }
}
