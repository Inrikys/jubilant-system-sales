package com.study.security.service.impl;

import com.study.security.dto.OrderDto;
import com.study.security.dto.OrderItemDto;
import com.study.security.exception.BusinessException;
import com.study.security.model.Customer;
import com.study.security.model.Order;
import com.study.security.model.OrderItem;
import com.study.security.model.Product;
import com.study.security.repository.CustomerRepository;
import com.study.security.repository.OrderItemRepository;
import com.study.security.repository.OrderRepository;
import com.study.security.repository.ProductRepository;
import com.study.security.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
// Create a constructor with all required parameters (final)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    @Transactional
    public Order save(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new BusinessException("Customer not found."));

        Order order = Order.builder()
                .total(orderDto.getTotal())
                .orderDate(LocalDate.now())
                .customer(customer)
                .build();

        List<OrderItem> orderItems = this.processOrderItem(order, orderDto.getOrderItemDtos());

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);
        order.setOrderItems(orderItems);
        return order;
    }

    private List<OrderItem> processOrderItem(Order order, List<OrderItemDto> items) {
        if (items.isEmpty()) {
            throw new BusinessException("To save an order is required at least one product");
        }
        return items.stream().map(dto -> {

            Product product = productRepository.findById(dto.getProductId())
                    .orElseThrow(() -> new BusinessException("Invalid product code: " + dto.getProductId()));

            OrderItem orderItem = OrderItem.builder()
                    .quantity(dto.getQuantity())
                    .order(order)
                    .product(product)
                    .build();

            return orderItem;
        }).collect(Collectors.toList());
    }
}
