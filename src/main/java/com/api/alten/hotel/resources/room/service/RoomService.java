package com.api.alten.hotel.resources.room.service;

import com.api.alten.hotel.resources.reservation.ReservationRequest;
import com.api.alten.hotel.resources.room.entity.Room;

/**
 * Interface class that define and manage methods signatures for RoomServiceImpl.
 * @author Alexsandro Saraiva
 */
public interface RoomService {

    public Room getRoom();

    public boolean checkAvailability(ReservationRequest reservationRequest);
}
