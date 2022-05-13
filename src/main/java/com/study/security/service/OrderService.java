package com.study.security.service;

import com.study.security.enums.OrderStatus;
import com.study.security.request.OrderRequest;
import com.study.security.model.Order;

import java.util.Optional;

public interface OrderService {
    Order save(OrderRequest orderRequest);

    Optional<Order> getOrderInfo(Long id);

    Order updateStatus(Long id, OrderStatus status);
}
