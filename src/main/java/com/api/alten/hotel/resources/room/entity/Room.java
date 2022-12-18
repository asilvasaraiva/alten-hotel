package com.api.alten.hotel.resources.room.entity;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "room")
@Data
public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1447667005044482159L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;

    @Column(name = "available")
    private boolean available = true;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservation;
}
