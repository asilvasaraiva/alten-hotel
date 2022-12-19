package com.api.alten.hotel.resources.dateTable.repository;

import com.api.alten.hotel.resources.dateTable.entity.DateTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface DateTableRepository extends JpaRepository<DateTable,Long> {
    @Query("SELECT dt.bookedDate FROM DateTable dt WHERE dt.bookedDate IN (:dateInterval) ")
    List<LocalDate> findByBookedDateIn(List<LocalDate> dateInterval);

    List<DateTable> findByReservationCode(Long reservationCode);

}
