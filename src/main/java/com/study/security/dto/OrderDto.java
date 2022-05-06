package com.study.security.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderDto {

    private Long customerId;
    private BigDecimal total;
    private List<OrderItemDto> orderItemDtos;

}
