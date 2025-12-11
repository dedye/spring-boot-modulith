package com.example.inventory.internal;

import com.example.inventory.source.entity.InventoryItem;
import com.example.inventory.source.repository.InventoryRepository;
import com.example.transaction.event.TransactionCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryItem createInventoryItem(String productId, Integer quantity) {
        InventoryItem item = new InventoryItem(productId, quantity);
        return inventoryRepository.save(item);
    }

    public boolean isAvailable(String productId, Integer quantity) {
        return inventoryRepository.findByProductId(productId)
            .map(item -> item.getAvailableQuantity() >= quantity)
            .orElse(false);
    }

    public void reserveInventory(String productId, Integer quantity) {
        InventoryItem item = inventoryRepository.findByProductId(productId)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (item.getAvailableQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient inventory");
        }

        item.setReservedQuantity(item.getReservedQuantity() + quantity);
        inventoryRepository.save(item);
    }

    //@ApplicationModuleListener
    //@TransactionalEventListener
    //@Async
    @EventListener
    void on(TransactionCreatedEvent event) {
        System.out.println("Received order created event for order: " + event.orderId());
    }
}