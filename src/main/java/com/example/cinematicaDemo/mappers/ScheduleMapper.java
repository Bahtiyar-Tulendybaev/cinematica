package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.ScheduleDto;
import com.example.cinematicaDemo.models.entities.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule, ScheduleDto>{
    ScheduleMapper INSTANCE = Mappers.getMapper(ScheduleMapper.class);
}
