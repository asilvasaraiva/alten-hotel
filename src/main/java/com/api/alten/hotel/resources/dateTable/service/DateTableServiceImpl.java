package com.api.alten.hotel.resources.dateTable.service;

import com.api.alten.hotel.resources.dateTable.entity.DateTable;
import com.api.alten.hotel.resources.dateTable.repository.DateTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DateTableServiceImpl implements DateTableService{

    @Autowired
    private DateTableRepository dateTableRepository;

    @Override
    public boolean checkAvailability(List<LocalDate> intervalOfDates) {
        return dateTableRepository.findByBookedDateIn(intervalOfDates).size() == 0;
    }

    public void saveDates(Long reservationCode,LocalDate date){
        DateTable dt = DateTable.builder().reservationCode(reservationCode).bookedDate(date).build();
        dateTableRepository.save(dt);
    }
}
