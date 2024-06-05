package com.abdu.productservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
    private String name;
    private String description;
    private Double price;
}
