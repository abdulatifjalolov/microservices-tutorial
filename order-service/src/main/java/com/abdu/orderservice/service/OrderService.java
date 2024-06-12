package com.abdu.orderservice.service;

import brave.Span;
import brave.Tracer;

import com.abdu.orderservice.dto.InventoryResponseDTO;
import com.abdu.orderservice.dto.OrderRequestDTO;
import com.abdu.orderservice.model.Order;
import com.abdu.orderservice.model.OrderLineItems;
import com.abdu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;

    public String placeOrder(OrderRequestDTO orderRequestDTO) {
        List<OrderLineItems> orderLineItems =
                orderRequestDTO.getOrderLineItemsDTOList().stream().map(OrderLineItems::of).toList();
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItems)
                .build();
        List<String> orderSkuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        log.info("Calling inventory service");
        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");
        try (Tracer.SpanInScope spanInScope = tracer.withSpanInScope(inventoryServiceLookup.start())) {
            InventoryResponseDTO[] inventoryResponseDTOS = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCodesList", orderSkuCodes)
                                    .build())
                    .retrieve()
                    .bodyToMono(InventoryResponseDTO[].class)
                    .block();
            boolean result = Arrays.stream(inventoryResponseDTOS).allMatch(InventoryResponseDTO::isInStock);
            if (Boolean.TRUE.equals(result)) {
                orderRepository.save(order);
                return "Order placed successfully";
            } else {
                throw new IllegalArgumentException("Product is not in stock, please try again later");
            }
        } finally {
            inventoryServiceLookup.finish();
        }
    }


}
