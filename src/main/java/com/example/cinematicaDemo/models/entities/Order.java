package com.example.cinematicaDemo.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_order")
public class Order extends WorkDate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    LocalDateTime orderDate;

    @PrePersist
    protected void onCreateOrderDate() {
        orderDate = LocalDateTime.now();
    }

}
