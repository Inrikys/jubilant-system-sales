package com.study.security.controller;

import com.study.security.enums.OrderStatus;
import com.study.security.exception.BusinessException;
import com.study.security.model.Order;
import com.study.security.request.OrderRequest;
import com.study.security.request.UpdateOrderStatusRequest;
import com.study.security.response.OrderResponse;
import com.study.security.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order save(@Valid @RequestBody OrderRequest orderRequest) {
        return orderService.save(orderRequest);
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) {
        return orderService.getOrderInfo(id)
                .map(o -> new OrderResponse(o))
                .orElseThrow(() -> new BusinessException("Order not found: " + id));
    }

    @PatchMapping("/{id}")
    public Order updateStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest statusRequest) {
        return orderService.updateStatus(id, OrderStatus.valueOf(statusRequest.getNewOrderStatus()));
    }
}
