package com.api.alten.hotel.resources.dateTable.repository;

import com.api.alten.hotel.resources.dateTable.entity.DateTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface class that manage the DataTable structure using JPA (Java Persistence API) to perform general queries such
 * save, delete,findById and many others.
 * @author Alexsandro Saraiva
 */
@Repository
@Transactional
public interface DateTableRepository extends JpaRepository<DateTable,Long> {

    /**
     * Method that implements a query to find and retrieve all dates in the given interval provided.
     * @param dateInterval A List of dates representing the interval to be searched in database.
     * @return A List with all the dates in the provided interval.
     */
    @Query("SELECT dt.bookedDate FROM DateTable dt WHERE dt.bookedDate IN (:dateInterval) ")
    List<LocalDate> findByBookedDateIn(List<LocalDate> dateInterval);

    /**
     * Method that implements a query to find and retrieve all DateTable objects with the reservation code provided.
     * @param reservationCode A unique number that identifies a reservation in the DateTable database.
     * @return A List with all the occurrences provided interval.
     */
    List<DateTable> findByReservationCode(Long reservationCode);

}
