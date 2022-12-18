package com.api.alten.hotel.resources.room.service;

import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.room.entity.Room;

public interface RoomService {

    public Room getRoom();

    public boolean checkAvailability(ReservationRequest reservationRequest);
}
