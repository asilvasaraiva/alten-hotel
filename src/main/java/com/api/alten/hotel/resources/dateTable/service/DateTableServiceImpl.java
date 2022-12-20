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

/**
 * Class that implements the methods signatures defined in DateTableService.
 * @author Alexsandro Saraiva
 */
@Service
@Slf4j
public class DateTableServiceImpl implements DateTableService{

    @Autowired
    private DateTableRepository dateTableRepository;


    /**
     * The implementation of a method that verify if a range of days is available or not to be booked.
     * @param checkIn Input check-in date value.
     * @param checkOut Input check-out date value.
     */
    @Override
    public void checkAvailability(LocalDate checkIn,LocalDate checkOut) {
        var occurrences = findOccurrences(checkIn, checkOut);
        if(!(occurrences.size() == 0)){
            throw new UnavailableDateException("Dates "+ occurrences.toString() +" already reserved");
        }
    }

    /**
     * The implementation of a method that retrieve a list of dates between check-in and check-out if
     * they exist in database.
     * @param checkIn Input check-in date value.
     * @param checkOut Input check-out date value.
     * @return A List of LocalDate object instance with reservation code and the date associated with.
     */
    @Override
    @Transactional
    public List<LocalDate> findOccurrences(LocalDate checkIn, LocalDate checkOut) {
        var intervalOfDates = checkIn.equals(checkOut)? List.of(checkIn) : checkIn.datesUntil(checkOut).toList();
        return dateTableRepository.findByBookedDateIn(intervalOfDates);
    }

    /**
     * The implementation of a method that persist the dates between check-in and check-out with the
     * reservation code in database.
     * @param checkIn LocalDate check-in date value.
     * @param checkOut LocalDate check-out date value.
     * @param reservationCode Long reservation code value.
     */
    @Override
    public void saveInDateTable(LocalDate checkIn, LocalDate checkOut, Long reservationCode) {
        var intervalOfDates = checkIn.datesUntil(checkOut).toList();
        if(intervalOfDates.size()>1){
            intervalOfDates.forEach(d-> saveDates(reservationCode,d));
        }else{
            saveDates(reservationCode,checkIn);
        }
    }

    /**
     * The implementation of a method that update the dates between check-in and check-out with the
     * reservation code in database for an existent reservation.
     * @param newCheckIn LocalDate check-in date value.
     * @param newCheckOut LocalDate check-out date value.
     * @param reservationCode Long reservation code value.
     */
    @Override
    public void updateDateTable(Long reservationCode, LocalDate newCheckIn, LocalDate newCheckOut) {
        var periodOfDays = Period.between(newCheckIn,newCheckOut).getDays();
        var listOfSavedDates = dateTableRepository.findByReservationCode(reservationCode);
        var occurrences = findOccurrences(newCheckIn, newCheckOut);
        var newSetOfDays = newSetOfDays(newCheckIn,newCheckOut);

        if (occurrences.size() == 0) {
            saveInDateTable(newCheckIn, newCheckOut, reservationCode);
            listOfSavedDates.forEach(s -> dateTableRepository.delete(s));
        }else if(periodOfDays > listOfSavedDates.size()){
            increaseReservationDays(listOfSavedDates, reservationCode, newSetOfDays);
        }else{
            decreaseReservationDays(listOfSavedDates, newSetOfDays);
        }
    }

    /**
     * Utility method implementation for updateDateTable function that deals when the client need to increase the
     * amount of days in his/her reservation.
     * @param listOfSavedDates List of DateTable with the dates already saved in database for this reservation.
     * @param newSetOfDays List of LocalDate with the new set of days to be inserted in the same reservation.
     * @param reservationCode Long reservation code value.
     * @return A boolean value statement.
     */
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

    /**
     * Utility method implementation for updateDateTable function that deals when the client need to decrease the
     * amount of days in his/her reservation.
     * @param listOfSavedDates List of DateTable with the dates already saved in database for this reservation.
     * @param newSetOfDays List of LocalDate with the new set of days to be modified in the same reservation.
     * @return A boolean value statement.
     */
    @Transactional
    private boolean decreaseReservationDays(List<DateTable>listOfSavedDates,List<LocalDate> newSetOfDays){
        for(DateTable dt : listOfSavedDates){
            if(!newSetOfDays.contains(dt.getBookedDate())){
                dateTableRepository.delete(dt);
            }
        }
        return true;
    }

    /**
     * Utility method implementation for updateDateTable function that generate a list with the new range of days
     * selected to modify a existent reservation
     * @param newCheckIn List of DateTable with the dates already saved in database for this reservation.
     * @param newCheckOut List of LocalDate with the new set of days to modify the same reservation.
     * @return A list with the range of days between the check-in day until check-out day.
     */
    private List<LocalDate> newSetOfDays(LocalDate newCheckIn, LocalDate newCheckOut){
        return newCheckIn.datesUntil(newCheckOut).toList();
    }

    /**
     * Utility method implementation for updateDateTable function that removes the dates already chosen by the
     * same person when modifying a reservation period of date.
     * @param A List of DateTable with the dates already saved in database for this reservation.
     * @param B List of LocalDate with the new set of days chosen to modify the same reservation.
     * @return A list with the new range of days between the new check-in day until new check-out day excepting
     * the days previously booked.
     */
    private List<LocalDate> elementsANotInB(List<LocalDate> A,List<LocalDate> B){
        return A.stream().filter(element -> !B.contains(element)).toList();
    }

    /**
     * The implementation of a method that save the date and reservation code in DataTable database.
     * @param reservationCode Long reservation code value.
     * @param date LocalDate date value.
     */
    @Transactional
    public void saveDates(Long reservationCode,LocalDate date){
        log.info("Saving chosen date {} and reservation {} into Data Table", date, reservationCode);
        var dt = DateTable.builder().reservationCode(reservationCode).bookedDate(date).build();
        dateTableRepository.save(dt);
        log.info("Chosen date {} and reservation {} saved successfully into Data Table", date, reservationCode);
    }

    /**
     * The implementation of a method that delete a DataTable object in database using the reservation code.
     * @param reservationCode Long reservation code value.
     */
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
