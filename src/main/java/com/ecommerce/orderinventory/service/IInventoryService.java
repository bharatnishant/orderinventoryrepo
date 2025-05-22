package com.ecommerce.orderinventory.service;

import com.ecommerce.orderinventory.dto.ProductDetails;

import java.util.List;

public interface IInventoryService {
    void restoreInventory(Long productId, int quantity);

    List<ProductDetails> getAllProductDetails();
}
