package com.study.security.response;

import com.study.security.model.Order;
import com.study.security.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private String cpf;
    private String customerName;
    private List<OrderItemResponse> orderItems;
    private BigDecimal total;
    private String orderDate;
    private String status;

    public OrderResponse(Order entity) {
        this.id = entity.getId();
        this.cpf = entity.getCustomer().getCpf();
        this.customerName = entity.getCustomer().getName();
        this.total = entity.getTotal();
        this.orderDate = entity.getOrderDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.status = entity.getStatus().name();

        if (CollectionUtils.isEmpty(entity.getOrderItems())) {
            this.orderItems = Collections.emptyList();
        }

        this.orderItems = entity.getOrderItems().stream()
                .map(o -> new OrderItemResponse(o)).collect(Collectors.toList());
    }

}
