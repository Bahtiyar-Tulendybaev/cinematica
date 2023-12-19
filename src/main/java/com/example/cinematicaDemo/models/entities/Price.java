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
@Table(name = "tb_price")
public class Price extends WorkDate{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double price;
    @Enumerated(EnumType.STRING)
    PriceType priceType;

}
