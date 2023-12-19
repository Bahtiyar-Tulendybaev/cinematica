package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.OrderDetailDto;
import com.example.cinematicaDemo.models.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail, OrderDetailDto>{
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

}
