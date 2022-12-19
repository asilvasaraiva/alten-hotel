package com.api.alten.hotel.resources.dateTable.service;

import java.time.LocalDate;
import java.util.List;

public interface DateTableService {

    public boolean checkAvailability(LocalDate checkIn,LocalDate checkOut);

    public List<LocalDate> findOccurrences(LocalDate checkIn, LocalDate checkOut);

    public void saveInDateTable(LocalDate checkIn,LocalDate checkOut,Long reservationCode);

    public boolean updateDateTable(Long reservationCode,LocalDate newCheckIn, LocalDate newCheckOut);

    public void saveDates(Long reservationCode, LocalDate date);
}

