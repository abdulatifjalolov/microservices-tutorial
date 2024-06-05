package com.abdu.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class InventoryResponseDTO {
    private boolean isInStock;
    private String skuCode;
}
