package com.example.cinematicaDemo.service;

import com.example.cinematicaDemo.models.dto.OrderDetailDto;
import com.example.cinematicaDemo.models.responses.OrderDetailResponse;

import java.util.List;

public interface OrderDetailService extends BaseService<OrderDetailDto>{
    List<OrderDetailResponse> getOrderDetailRes(Long orderDetailId);
    List<OrderDetailDto> findByOrderId(Long orderId);
}
