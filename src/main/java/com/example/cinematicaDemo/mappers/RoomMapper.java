package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.RoomDto;
import com.example.cinematicaDemo.models.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface RoomMapper extends BaseMapper<Room, RoomDto>{
    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);
}
