package com.ecommerce.orderinventory.controller;

import com.ecommerce.orderinventory.dto.ApiResponse;
import com.ecommerce.orderinventory.dto.OrderCancellationRequest;
import com.ecommerce.orderinventory.dto.OrderDetails;
import com.ecommerce.orderinventory.dto.ProductDetails;
import com.ecommerce.orderinventory.service.impl.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderDetails>> getAllOrders() {
        List<OrderDetails> orders = orderService.getAllOrderDetails();
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/cancel")
    public ResponseEntity<ApiResponse> cancelOrder(@RequestBody OrderCancellationRequest orderCancellationRequest) {
        orderService.cancelOrder(orderCancellationRequest.getOrderId());
        return ResponseEntity.ok(new ApiResponse("Order cancelled successfully"));
    }
}

