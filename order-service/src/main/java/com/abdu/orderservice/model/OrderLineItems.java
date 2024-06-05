package com.abdu.orderservice.model;

import com.abdu.orderservice.dto.OrderLineItemsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer quantity;
    private BigDecimal price;

    public static OrderLineItems of(OrderLineItemsDTO orderLineItemsDTO) {
        return OrderLineItems.builder()
                .price(orderLineItemsDTO.getPrice())
                .skuCode(orderLineItemsDTO.getSkuCode())
                .quantity(orderLineItemsDTO.getQuantity())
                .build();
    }
}
