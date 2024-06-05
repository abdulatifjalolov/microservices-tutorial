package com.abdu.orderservice.service;

import com.abdu.orderservice.dto.InventoryResponseDTO;
import com.abdu.orderservice.dto.OrderRequestDTO;
import com.abdu.orderservice.model.Order;
import com.abdu.orderservice.model.OrderLineItems;
import com.abdu.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequestDTO orderRequestDTO) {
        List<OrderLineItems> orderLineItems =
                orderRequestDTO.getOrderLineItemsDTOList().stream().map(OrderLineItems::of).toList();
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItems)
                .build();
        List<String> orderSkuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

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
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }


    }


}
