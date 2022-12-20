package com.api.alten.hotel.service;

import com.api.alten.hotel.exceptions.InvalidCheckInDateException;
import com.api.alten.hotel.exceptions.LimitOfDaysException;
import com.api.alten.hotel.exceptions.NotFoundException;
import com.api.alten.hotel.resources.dateTable.service.DateTableService;
import com.api.alten.hotel.resources.reservation.repository.ReservationRepository;
import com.api.alten.hotel.resources.reservation.service.ReservationService;
import com.api.alten.hotel.resources.reservation.service.ReservationServiceImpl;
import com.api.alten.hotel.resources.room.service.RoomService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class BookingTest {

    @InjectMocks
    private ReservationServiceImpl reservationServiceImp;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private DateTableService dataTable;


    @Test
    public void shouldCreateBookingSuccessfully(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var request = DataFactory.buildReservationRequest();
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);
        var room = DataFactory.buildRoom();

        when(roomService.getRoom()).thenReturn(room);

        var result = reservationServiceImp.createReservation(request.getClientName(),checkIn,checkOut);

        Assertions.assertNotNull(result);
        Assertions.assertNotEquals(expectedReservation.getReservationCode(),result.getReservationCode());
    }

    @Test
    public void shouldThrowInvalidCheckInExceptionWhenCreateReservation(){
        var checkIn = DataFactory.buildDate(0);
        var checkOut = DataFactory.buildDate(5);
        final var reservationRequest = DataFactory.buildReservationRequest(checkIn,checkOut,null);
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> bookingService.createBooking(reservationRequest))
                .isInstanceOf(InvalidCheckInDateException.class);
    }

    @Test
    public void shouldThrowLimitOfDaysExceptionWhenCreateReservation(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        final var reservationRequest = DataFactory.buildReservationRequest(checkIn,checkOut.plusMonths(2),null);
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> bookingService.createBooking(reservationRequest))
                .isInstanceOf(LimitOfDaysException.class);
    }

    @Test
    public void shouldModifyBookingSuccessfully(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var request = DataFactory.buildReservationRequest();
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);

        var room = DataFactory.buildRoom();

        when(reservationService.modifyReservation(expectedReservation.getReservationCode(),checkIn,checkOut)).thenReturn(HttpStatus.OK);


        var result = bookingService.modifyBooking(expectedReservation.getReservationCode(),request);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("200 OK",result.toString());
    }

    @Test
    public void shouldModifyReservationSuccessfully(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);
        var room = DataFactory.buildRoom();

        when(reservationServiceImp.getReservationByCode(expectedReservation.getReservationCode())).thenReturn(expectedReservation);

        var result = reservationServiceImp.modifyReservation(expectedReservation.getReservationCode(),checkIn,checkOut);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("200 OK",result.toString());
    }

    @Test
    public void shouldNotModifyReservation(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);

        var result = reservationServiceImp.modifyReservation(null,checkIn,checkOut);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("304 NOT_MODIFIED",result.toString());
    }

    @Test
    public void shouldCancelBookingSuccessfully(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);
        var room = DataFactory.buildRoom();


        when(reservationService.cancelReservation(expectedReservation.getReservationCode())).thenReturn(HttpStatus.OK);

        var result = bookingService.cancelBooking(expectedReservation.getReservationCode());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("200 OK",result.toString());
    }

    @Test
    public void shouldCancelReservationSuccessfully(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);
        var room = DataFactory.buildRoom();

        when(reservationRepository.findByReservationCode(expectedReservation.getReservationCode())).thenReturn(expectedReservation);

        var result = reservationServiceImp.cancelReservation(expectedReservation.getReservationCode());

        Assertions.assertNotNull(result);
        Assertions.assertEquals("200 OK",result.toString());
    }

    @Test
    public void shouldNotCancelReservation(){
        var checkIn = DataFactory.buildDate(1);
        var checkOut = DataFactory.buildDate(2);
        var expectedReservation = DataFactory.buildReservation(checkIn,checkOut);
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> reservationServiceImp.cancelReservation(null))
                .isInstanceOf(NotFoundException.class);
    }
}
