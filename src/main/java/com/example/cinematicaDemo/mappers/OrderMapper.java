package com.example.cinematicaDemo.mappers;

import com.example.cinematicaDemo.models.dto.OrderDto;
import com.example.cinematicaDemo.models.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);


}
