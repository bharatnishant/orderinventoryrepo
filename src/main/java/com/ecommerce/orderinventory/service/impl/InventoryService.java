package com.ecommerce.orderinventory.service.impl;

import com.ecommerce.orderinventory.dto.ProductDetails;
import com.ecommerce.orderinventory.entity.Inventory;
import com.ecommerce.orderinventory.repository.InventoryRepository;
import com.ecommerce.orderinventory.service.IInventoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryService implements IInventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void restoreInventory(Long productId, int quantity) {
        Optional<Inventory> inventory = inventoryRepository.findByProductId(productId);
        inventory.equals(inventory.get().getQuantity() - quantity);
        inventory.get().setUpdatedAt(LocalDateTime.now());
        inventoryRepository.save(inventory.get());
    }

    @Override
    public List<ProductDetails> getAllProductDetails() {
        return inventoryRepository.findAll().stream()
                .map(inv -> new ProductDetails(
                        inv.getProductId(),
                        inv.getProductName(),
                        inv.getProductCategory(),
                        inv.getQuantity(),
                        inv.getUnitPrice()
                ))
                .collect(Collectors.toList());
    }
}
