package com.study.security.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderRequest {

    private Long customerId;
    private BigDecimal total;
    private List<OrderItemRequest> items;

}
