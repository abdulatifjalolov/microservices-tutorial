package com.abdu.inventoryservice;

import com.abdu.inventoryservice.model.Inventory;
import com.abdu.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory = Inventory.builder()
                    .skuCode("Iphone 15 pro max")
                    .quantity(100)
                    .build();
            Inventory inventory1 = Inventory.builder()
                    .skuCode("Iphone 15 pro")
                    .quantity(100)
                    .build();
            inventoryRepository.saveAll(List.of(inventory, inventory1));
        };
    }
}
