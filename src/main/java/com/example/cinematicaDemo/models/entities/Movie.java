package com.example.cinematicaDemo.models.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tb_movie")
public class Movie extends WorkDate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String definition;
    String name;
    Double rating;
    String pg;
    String image;


}
