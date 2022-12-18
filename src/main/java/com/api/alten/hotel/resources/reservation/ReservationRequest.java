package com.api.alten.hotel.resources.reservation;


import lombok.Data;

@Data
public class ReservationRequest {
    public String clientName;
    public String checkIn;
    public String checkOut;
    public Long reservationCode;
}
