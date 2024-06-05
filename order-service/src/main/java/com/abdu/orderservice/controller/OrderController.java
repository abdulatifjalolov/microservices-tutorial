package com.abdu.orderservice.controller;

import com.abdu.orderservice.dto.OrderRequestDTO;
import com.abdu.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(
            @RequestBody OrderRequestDTO orderRequestDTO
    ) {
        orderService.placeOrder(orderRequestDTO);
        return "Order placed successfully";
    }
}
