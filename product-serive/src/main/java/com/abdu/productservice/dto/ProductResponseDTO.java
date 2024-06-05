package com.abdu.productservice.dto;

import com.abdu.productservice.model.Product;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private Double price;

    public static ProductResponseDTO of(Product product) {
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
