package com.study.security.response;

import com.study.security.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemResponse {

    private String description;
    private BigDecimal price;
    private Integer quantity;

    public OrderItemResponse(OrderItem entity){
        this.description = entity.getProduct().getDescription();
        this.price = entity.getProduct().getPrice();
        this.quantity = entity.getQuantity();
    }

}
