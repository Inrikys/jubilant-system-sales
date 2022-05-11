package com.study.security.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderItemRequest {
    private Long productId;
    private Integer quantity;
}
