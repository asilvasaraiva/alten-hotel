package com.api.alten.hotel.resources.reservation.service;



import com.api.alten.hotel.exceptions.UnavailableDateException;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.resources.reservation.repository.ReservationRepository;
import com.api.alten.hotel.resources.room.entity.Room;
import com.api.alten.hotel.resources.room.repository.RoomRepository;
import com.api.alten.hotel.resources.room.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Random;

@Service
@Slf4j
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomService roomService;

    @Transactional
    public void save(Reservation reservation){
        try {
            log.info("Saving reservation to client: {} in room {}", reservation.getClientName(),reservation.getRoom().getId());
            reservationRepository.save(reservation);
            log.info("Reservation to client {} in room {} saved successfully ", reservation.getClientName(),reservation.getRoom().getId());
        }catch (Exception e){
            throw new UnavailableDateException("Error to save reservation with reservation code "+ reservation.getReservationCode()+" in database");
        }
    }


    public Reservation createReservation(String client, LocalDate checkIn,LocalDate checkOut){
        var room = roomService.getRoom();
        var book = new Reservation();
        book.setClientName(client);
        book.setRoom(room);
        book.setCheckIn(checkIn);
        book.setCheckOut(checkOut);
        book.setReservationCode(genReservationCode());
        save(book);
        return book;
    }

    private long genReservationCode(){
        Random random = new Random();
        return random.nextInt(10000);
    }

}
