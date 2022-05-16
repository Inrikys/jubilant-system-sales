package com.study.security.request;

import com.study.security.annotation.NotEmptyList;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderRequest {

    @NotNull(message = "Customer message is required.")
    private Long customerId;

    @NotNull(message = "Total is required.")
    private BigDecimal total;

    @NotEmptyList(message = "Order items are required.")
    private List<OrderItemRequest> items;

}
