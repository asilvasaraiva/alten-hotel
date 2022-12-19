package com.api.alten.hotel.resources.reservation.entity;

import com.api.alten.hotel.resources.dateTable.entity.DateTable;
import com.api.alten.hotel.resources.room.entity.Room;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Table(name = "reservation")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation implements Serializable {
    @Serial
    private static final long serialVersionUID = 5898961581308962828L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnore
    @ToString.Exclude
    private Room room;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "reservation_code")
    private Long reservationCode;

    @Column(name = "checkIn")
    private LocalDate checkIn;

    @Column(name = "checkOut")
    private LocalDate checkOut;

}
