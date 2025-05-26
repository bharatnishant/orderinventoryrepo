package com.ecommerce.orderinventory.service.impl;


import com.ecommerce.orderinventory.dto.OrderDetails;
import com.ecommerce.orderinventory.entity.Orders;
import com.ecommerce.orderinventory.repository.OrdersRepository;
import com.ecommerce.orderinventory.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final OrdersRepository ordersRepository;
    private final InventoryService inventoryService;

    public OrderService(OrdersRepository ordersRepository, InventoryService inventoryService) {
        this.ordersRepository = ordersRepository;
        this.inventoryService = inventoryService;
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
        return ordersRepository.findAll().stream()
                .map(order -> new OrderDetails(
                        order.getId(),
                        order.getCustomerId(),
                        order.getProductId(),
                        order.getQuantity(),
                        order.getOrderDate(),
                        order.getStatus(),
                        order.getExpectedDelivery()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void cancelOrder(Long orderId) {
        log.info("Cancelling order with ID = {}", orderId);
        Optional<Orders> order = ordersRepository.findById(orderId);

        order.get().setStatus("CANCELLED");
        ordersRepository.save(order.get());

        inventoryService.restoreInventory(order.get().getProductId(), order.get().getQuantity());
        log.info("Order ID {} successfully cancelled.", orderId);
    }
}
