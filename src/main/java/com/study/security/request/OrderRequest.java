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

    @NotNull(message = "{field.customer-id.required}")
    private Long customerId;

    @NotNull(message = "{field.order-total.required}")
    private BigDecimal total;

    @NotEmptyList(message = "{field.order-items.required}")
    private List<OrderItemRequest> items;

}
