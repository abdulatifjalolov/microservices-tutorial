package com.abdu.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItemsDTO {
    private Long id;
    private String skuCode;
    private Integer quantity;
    private BigDecimal price;
}
