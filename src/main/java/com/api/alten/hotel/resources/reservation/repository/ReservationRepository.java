package com.api.alten.hotel.resources.reservation.repository;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    Reservation findByReservationCode(Long code);
}
