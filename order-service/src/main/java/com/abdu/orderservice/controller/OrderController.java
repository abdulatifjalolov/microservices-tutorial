package com.abdu.orderservice.controller;

import com.abdu.orderservice.dto.OrderRequestDTO;
import com.abdu.orderservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public String placeOrder(
            @RequestBody OrderRequestDTO orderRequestDTO
    ) {
        return orderService.placeOrder(orderRequestDTO);
    }

    public CompletableFuture<String> fallbackMethod(OrderRequestDTO orderRequestDTO, RuntimeException exception) {
        return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please try again after some time");
    }
}
