package com.study.security.controller;

import com.study.security.dto.OrderDto;
import com.study.security.model.Order;
import com.study.security.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save (@RequestBody OrderDto orderDto){
        Order order = orderService.save(orderDto);
        return ResponseEntity.ok().body(order);
    }

}
