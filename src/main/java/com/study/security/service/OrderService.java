package com.study.security.service;

import com.study.security.dto.OrderDto;
import com.study.security.model.Order;

public interface OrderService {
    Order save(OrderDto orderDto);
}
