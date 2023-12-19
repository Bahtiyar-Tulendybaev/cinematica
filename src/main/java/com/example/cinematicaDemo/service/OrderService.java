package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.enums.PriceType;
import com.example.cinematicaDemo.models.dto.OrderDetailDto;
import com.example.cinematicaDemo.models.dto.OrderDto;
import com.example.cinematicaDemo.models.responses.OrderResponse;

import java.util.List;
import java.util.Map;

public interface OrderService extends BaseService<OrderDto>{
    OrderResponse book(Long roomMovieId, Map<Long,PriceType> seatListAndPriceType);
    OrderDto create();
    OrderResponse getOrderDetail(List<OrderDetailDto> orderDetailDtoList);




}
