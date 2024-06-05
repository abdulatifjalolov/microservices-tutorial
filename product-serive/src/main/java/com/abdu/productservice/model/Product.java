package com.abdu.productservice.model;

import com.abdu.productservice.dto.ProductRequestDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;

    public static Product of(ProductRequestDTO productRequestDTO) {
        return Product.builder()
                .name(productRequestDTO.getName())
                .description(productRequestDTO.getDescription())
                .price(productRequestDTO.getPrice())
                .build();
    }
}



