package com.abdu.inventoryservice.controller;

import com.abdu.inventoryservice.dto.InventoryResponseDTO;
import com.abdu.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponseDTO> isInStock(
            @RequestParam List<String> skuCodesList
    ) {
        return inventoryService.isInStock(skuCodesList);
    }
}
