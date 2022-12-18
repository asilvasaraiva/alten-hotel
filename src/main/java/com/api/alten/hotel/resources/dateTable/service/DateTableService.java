package com.api.alten.hotel.resources.dateTable.service;

import java.time.LocalDate;
import java.util.List;

public interface DateTableService {

    public boolean checkAvailability(List<LocalDate> intervalOfDates);
    public void saveDates(Long reservationCode,LocalDate date);
}
