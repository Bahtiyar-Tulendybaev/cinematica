package com.example.cinematicaDemo.models.entities;

import com.example.cinematicaDemo.enums.PriceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_order_detail")
public class OrderDetail extends WorkDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    SeatSchedule seatSchedule;
    @ManyToOne
    Order order;
    @Enumerated(EnumType.STRING)
    PriceType priceType;






}
