package com.api.alten.hotel.resources.dateTable.service;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface class that define and manage methods signatures for DateTableServiceImpl.
 * @author Alexsandro Saraiva
 */

public interface DateTableService {

    public void checkAvailability(LocalDate checkIn,LocalDate checkOut);

    public List<LocalDate> findOccurrences(LocalDate checkIn, LocalDate checkOut);

    public void saveInDateTable(LocalDate checkIn,LocalDate checkOut,Long reservationCode);

    public void updateDateTable(Long reservationCode, LocalDate newCheckIn, LocalDate newCheckOut);

    public void saveDates(Long reservationCode, LocalDate date);

    public void deleteByReservationCode(Long reservationCode);
}

