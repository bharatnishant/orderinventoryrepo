package com.ecommerce.orderinventory.service;

import com.ecommerce.orderinventory.dto.OrderDetails;

import java.util.List;

public interface IOrderService {
    List<OrderDetails> getAllOrderDetails();

    void cancelOrder(Long orderId);
}
