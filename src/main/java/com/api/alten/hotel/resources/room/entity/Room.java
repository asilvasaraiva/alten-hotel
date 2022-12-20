package com.api.alten.hotel.resources.room.entity;

import com.api.alten.hotel.resources.reservation.entity.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Entity class that represents a room.
 * @author Alexsandro Saraiva
 */
@Entity
@Table(name = "room")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room implements Serializable {
    @Serial
    private static final long serialVersionUID = 1447667005044482159L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;

    @Column(name = "name")
    private String name;


}
