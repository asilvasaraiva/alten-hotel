package com.api.alten.hotel.resources.reservation.repository;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface class that manage the Reservation structure using JPA (Java Persistence API) to perform general queries such
 * save, delete,findById and many others.
 * @author Alexsandro Saraiva
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    /**
     * Method that implements a query to find and retrieve a Reservation object with the reservation code provided.
     * @param reservationCode A unique number that identifies a reservation in the Reservation database.
     * @return A reservation associated with the reservation code informed.
     */
    Reservation findByReservationCode(Long reservationCode);
}
