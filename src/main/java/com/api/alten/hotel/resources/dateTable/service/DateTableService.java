package com.api.alten.hotel.resources.dateTable.service;

import java.time.LocalDate;
import java.util.List;

public interface DateTableService {

    public boolean checkAvailability(LocalDate checkIn,LocalDate checkOut);

    public  void updateDateTable(LocalDate checkIn,LocalDate checkOut,Long reservationCode);

    public void saveDates(Long reservationCode,LocalDate date);
}

