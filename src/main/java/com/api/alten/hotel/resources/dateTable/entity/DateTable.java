package com.api.alten.hotel.resources.dateTable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@Entity
@AllArgsConstructor
public class DateTable implements Serializable {

    @Serial
    private static final long serialVersionUID = -5986521926227569089L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_table_id")
    private Long id;

    private Long reservationCode;

    private LocalDate bookedDate;


}
