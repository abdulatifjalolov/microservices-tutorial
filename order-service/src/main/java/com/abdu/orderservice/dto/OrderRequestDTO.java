package com.abdu.orderservice.dto;

import com.abdu.orderservice.model.OrderLineItems;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
    private List<OrderLineItemsDTO> orderLineItemsDTOList;
}
