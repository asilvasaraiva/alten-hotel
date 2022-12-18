package com.api.alten.hotel.resources.dateTable.service;

import com.api.alten.hotel.exceptions.UnavailableDateException;
import com.api.alten.hotel.resources.dateTable.entity.DateTable;
import com.api.alten.hotel.resources.dateTable.repository.DateTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DateTableServiceImpl implements DateTableService{

    @Autowired
    private DateTableRepository dateTableRepository;

    @Override
    public boolean checkAvailability(LocalDate checkIn,LocalDate checkOut) {
        var occurrences = findOccurrences(checkIn, checkOut);
        if(occurrences.size() == 0){
            return true;
        }else{
            throw new UnavailableDateException("Dates "+ occurrences.toString() +" already reserved");
        }
    }

    @Override
    public List<LocalDate> findOccurrences(LocalDate checkIn, LocalDate checkOut) {
        var intervalOfDates = checkIn.equals(checkOut)? List.of(checkIn) : checkIn.datesUntil(checkOut).toList();
        return dateTableRepository.findByBookedDateIn(intervalOfDates);
    }

    @Override
    public void updateDateTable(LocalDate checkIn, LocalDate checkOut, Long reservationCode) {
        var intervalOfDates = checkIn.datesUntil(checkOut).toList();
        if(intervalOfDates.size()>1){
            intervalOfDates.forEach(d-> saveDates(reservationCode,d));
        }else{
            saveDates(reservationCode,checkIn);
        }
    }


    public void saveDates(Long reservationCode,LocalDate date){
        log.info("Saving chosen date {} and reservation {} into Data Table", date, reservationCode);
        var dt = DateTable.builder().reservationCode(reservationCode).bookedDate(date).build();
        dateTableRepository.save(dt);
        log.info("Chosen date {} and reservation {} saved successfully into Data Table", date, reservationCode);
    }
}
