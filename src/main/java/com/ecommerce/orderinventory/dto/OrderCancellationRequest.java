package com.ecommerce.orderinventory.dto;


public class OrderCancellationRequest {

    public OrderCancellationRequest(Long orderId) {
        this.orderId = orderId;
    }

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
