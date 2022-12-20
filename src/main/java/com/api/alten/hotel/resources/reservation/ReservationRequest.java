package com.api.alten.hotel.resources.reservation;


import lombok.Data;

/**
 * Class that represents a expected request structure and its fields to the API.
 * @author Alexsandro Saraiva
 */
@Data
public class ReservationRequest {
    public String clientName;
    public String checkIn;
    public String checkOut;
    public Long reservationCode;
}
