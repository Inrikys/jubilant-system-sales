package com.study.security.controller;

import com.study.security.exception.BusinessException;
import com.study.security.request.OrderRequest;
import com.study.security.model.Order;
import com.study.security.response.OrderResponse;
import com.study.security.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.save(orderRequest);
        return ResponseEntity.ok().body(order);
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.getOrderInfo(id)
                .map(o -> new OrderResponse(o))
                .orElseThrow(() -> new BusinessException("Order not found: " + id));
    }
}
