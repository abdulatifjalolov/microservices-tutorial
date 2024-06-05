package com.abdu.productservice.service;

import com.abdu.productservice.dto.ProductRequestDTO;
import com.abdu.productservice.dto.ProductResponseDTO;
import com.abdu.productservice.model.Product;
import com.abdu.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequestDTO productRequestDTO) {
        Product product = Product.of(productRequestDTO);
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponseDTO> listProducts() {
        List<Product> all = productRepository.findAll();
        return all.stream().map(ProductResponseDTO::of).toList();
    }
}
