package com.example.cinematicaDemo.models.responses;

import com.example.cinematicaDemo.enums.SeatStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SeatScheduleResponse {
    long seatScheduleId;
    SeatStatus status;
    int row;
    int seatNum;
}
