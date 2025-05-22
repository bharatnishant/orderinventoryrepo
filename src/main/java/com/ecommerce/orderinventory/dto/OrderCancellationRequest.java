package com.ecommerce.orderinventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderCancellationRequest {

    private Long orderId;

}
