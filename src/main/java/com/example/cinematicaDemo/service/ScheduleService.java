package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.ScheduleDto;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ScheduleService extends BaseService<ScheduleDto>{
    ScheduleDto create(LocalDate startDay, LocalTime startTime);

}
