package com.example.cinematicaDemo.models.entities;

import com.example.cinematicaDemo.enums.SeatStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_seat_schedule")
public class SeatSchedule extends WorkDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Seat seat;
    @ManyToOne
    RoomMovie roomMovie;
    @Enumerated(EnumType.STRING)
    SeatStatus seatStatus;

}
