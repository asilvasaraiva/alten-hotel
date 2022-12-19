package com.api.alten.hotel.resources.dateTable.service;

import com.api.alten.hotel.exceptions.UnavailableDateException;
import com.api.alten.hotel.resources.dateTable.entity.DateTable;
import com.api.alten.hotel.resources.dateTable.repository.DateTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    public List<LocalDate> findOccurrences(LocalDate checkIn, LocalDate checkOut) {
        var intervalOfDates = checkIn.equals(checkOut)? List.of(checkIn) : checkIn.datesUntil(checkOut).toList();
        return dateTableRepository.findByBookedDateIn(intervalOfDates);
    }

    @Override
    public void saveInDateTable(LocalDate checkIn, LocalDate checkOut, Long reservationCode) {
        var intervalOfDates = checkIn.datesUntil(checkOut).toList();
        if(intervalOfDates.size()>1){
            intervalOfDates.forEach(d-> saveDates(reservationCode,d));
        }else{
            saveDates(reservationCode,checkIn);
        }
    }

    @Override
    public boolean updateDateTable(Long reservationCode, LocalDate newCheckIn, LocalDate newCheckOut) {
        var periodOfDays = Period.between(newCheckIn,newCheckOut).getDays();
        var listOfSavedDates = dateTableRepository.findByReservationCode(reservationCode);
        var occurrences = findOccurrences(newCheckIn, newCheckOut);
        var newSetOfDays = newSetOfDays(newCheckIn,newCheckOut);

        if (occurrences.size() == 0) {
            saveInDateTable(newCheckIn, newCheckOut, reservationCode);
            listOfSavedDates.forEach(s -> dateTableRepository.delete(s));
            return true;
        }

        if(periodOfDays > listOfSavedDates.size()){
            return increaseReservationDays(listOfSavedDates,reservationCode,newSetOfDays);
        }else{
           return decreaseReservationDays(listOfSavedDates,newSetOfDays);
        }
    }

    private boolean increaseReservationDays(List<DateTable>listOfSavedDates,Long reservationCode,List<LocalDate> newSetOfDays){
        var daysToCheckAndSave = elementsANotInB(newSetOfDays,listOfSavedDates.stream().map(DateTable::getBookedDate).toList());
        var allDaysPermitted = dateTableRepository.findByBookedDateIn(daysToCheckAndSave);
        if(allDaysPermitted.size()==0){
            daysToCheckAndSave.forEach(r->saveDates(reservationCode,r));
            return true;
        }else {
            throw new UnavailableDateException("Dates already reserved");
        }
    }

    @Transactional
    private boolean decreaseReservationDays(List<DateTable>listOfSavedDates,List<LocalDate> newSetOfDays){
        for(DateTable dt : listOfSavedDates){
            if(!newSetOfDays.contains(dt.getBookedDate())){
                dateTableRepository.delete(dt);
            }
        }
        return true;
    }

    private List<LocalDate> newSetOfDays(LocalDate newCheckIn, LocalDate newCheckOut){
        return newCheckIn.datesUntil(newCheckOut).toList();
    }

    private List<LocalDate> elementsANotInB(List<LocalDate> A,List<LocalDate> B){
        return A.stream().filter(element -> !B.contains(element)).toList();
    }

    @Transactional
    public void saveDates(Long reservationCode,LocalDate date){
        log.info("Saving chosen date {} and reservation {} into Data Table", date, reservationCode);
        var dt = DateTable.builder().reservationCode(reservationCode).bookedDate(date).build();
        dateTableRepository.save(dt);
        log.info("Chosen date {} and reservation {} saved successfully into Data Table", date, reservationCode);
    }

    @Override
    @Transactional
    public void deleteByReservationCode(Long reservationCode) {
        var reservedDates = Optional.ofNullable(dateTableRepository.findByReservationCode(reservationCode));
        if(reservedDates.isPresent()){
            dateTableRepository.deleteAll(reservedDates.get());
        }else{
            throw new UnavailableDateException("Error to delete reservation "+reservationCode+ " from database.");
        }
    }

}
