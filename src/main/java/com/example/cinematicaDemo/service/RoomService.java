package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.RoomDto;
import com.example.cinematicaDemo.models.request.SaveRoomRequest;

public interface RoomService extends BaseService<RoomDto>{
    RoomDto create(SaveRoomRequest room);
    RoomDto findRoomByRoomMovieId(Long roomMovieId);

}
