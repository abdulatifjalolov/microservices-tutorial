package com.abdu.productservice.controller;

import com.abdu.productservice.dto.ProductRequestDTO;
import com.abdu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(
            @RequestBody ProductRequestDTO productRequestDTO
    ) {
        productService.createProduct(productRequestDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listProducts() {
        return ResponseEntity.ok(productService.listProducts());
    }
}

