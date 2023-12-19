package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.SeatScheduleDto;
import com.example.cinematicaDemo.models.entities.SeatSchedule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface SeatScheduleMapper extends BaseMapper<SeatSchedule, SeatScheduleDto> {
    SeatScheduleMapper INSTANCE = Mappers.getMapper(SeatScheduleMapper.class);
}
