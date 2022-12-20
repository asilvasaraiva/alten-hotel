package com.api.alten.hotel.resources.reservation.service;



import com.api.alten.hotel.exceptions.NotFoundException;
import com.api.alten.hotel.exceptions.UnavailableDateException;
import com.api.alten.hotel.resources.dateTable.service.DateTableService;
import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.api.alten.hotel.resources.reservation.repository.ReservationRepository;
import com.api.alten.hotel.resources.room.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

/**
 * Class that implements the methods signatures defined in ReservationService.
 * @author Alexsandro Saraiva
 */

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService{
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomService roomService;

    @Autowired
    private DateTableService dateTableService;

    /**
     * The implementation of a method that save a new reservation in database.
     * @param reservation Reservation object.
     */
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

    /**
     * The implementation of a method that create and saves a new reservation .
     * @param client String name of client.
     * @param checkIn LocalDate date of check-in.
     * @param checkOut LocalDate date of check-out.
     * @return Reservation object created if successful.
     */
    public Reservation createReservation(String client, LocalDate checkIn,LocalDate checkOut){
        dateTableService.checkAvailability(checkIn,checkOut);
        var room = roomService.getRoom();
        var book = Reservation.builder()
                .clientName(client)
                .room(room)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .reservationCode(genReservationCode())
                .build();
        save(book);

        dateTableService.saveInDateTable(checkIn,checkOut, book.getReservationCode());

        return book;
    }

    /**
     * Utility method implementation for createReservation function that generate the reservation code.
     * @return A Long value used as reservation code.
     */
    private long genReservationCode(){
        return System.currentTimeMillis();
    }

    /**
     * The implementation of a method that modifies new reservation .
     * @param reservationCode Long reservation code.
     * @param newCheckIn LocalDate new date of check-in.
     * @param newCheckOut LocalDate new date of check-out.
     * @return HttpStatus with the status.
     */
    public HttpStatus modifyReservation(Long reservationCode, LocalDate newCheckIn, LocalDate newCheckOut){
        var reservation = Optional.ofNullable(getReservationByCode(reservationCode));

        if(reservation.isPresent()){
            var currentReservation = reservation.get();
            dateTableService.updateDateTable(currentReservation.getReservationCode(),newCheckIn,newCheckOut);
            currentReservation.setCheckIn(newCheckIn);
            currentReservation.setCheckOut(newCheckOut);
            reservationRepository.save(reservation.get());
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_MODIFIED;
    }

    /**
     * Utility method implementation for modifyReservation function that retrieves a reservation based in
     * the reservation code.
     * @return A reservation object.
     */
    @Transactional
    private Reservation getReservationByCode(Long reservationCode){
        try {
            log.info("Retrieving reservation by reservation ID {} ", reservationCode);
            return reservationRepository.findByReservationCode(reservationCode);
        }catch (Exception e){
            throw new UnavailableDateException("Error to retrieve reservation with reservation code "+ reservationCode+" from database");
        }
    }

    /**
     * The implementation of a method that cancel an existent reservation .
     * @param reservationCode Long reservation code.
     * @return HttpStatus with the status.
     */

    public HttpStatus cancelReservation(Long reservationCode){
        var reservation = Optional.ofNullable(reservationRepository.findByReservationCode(reservationCode));

        if(reservation.isPresent()){
            dateTableService.deleteByReservationCode(reservationCode);
            reservationRepository.deleteById(reservation.get().getId());
            return HttpStatus.OK;
        }
        throw new NotFoundException();
    }
}
