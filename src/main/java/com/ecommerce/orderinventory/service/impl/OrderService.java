package com.ecommerce.orderinventory.service.impl;


import com.ecommerce.orderinventory.entity.Orders;
import com.ecommerce.orderinventory.repository.OrdersRepository;
import com.ecommerce.orderinventory.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService implements IOrderService {

    private final OrdersRepository ordersRepository;
    private final InventoryService inventoryService;

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
