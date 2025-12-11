package com.example.orders.internal;

import com.example.orders.source.entity.Orders;
import com.example.orders.source.repository.OrdersRepository;
import com.example.orders.spi.OrdersData;
import com.example.orders.spi.dto.OrdersDataDto;
import com.example.transaction.event.TransactionCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.math.BigDecimal;

@Service
public class OrdersService implements OrdersData {

    private final OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository ordersRepository) {
        // Spring injects the hidden InventoryServiceImpl here.
        this.ordersRepository = ordersRepository;
    }

    @EventListener
    //@ApplicationModuleListener
    //@TransactionalEventListener
    //@Async
    void on(TransactionCompletedEvent event) {
        Orders order = ordersRepository.findById(event.orderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setStatus("COMPLETED");
        ordersRepository.save(order);
        sendNotification(
                order.getCustomerId(),
                "Order Completed",
                "Your order #" + event.orderId() + " has been completed."
        );
    }

    private void sendNotification(String customerId, String subject, String message) {
        System.out.println("Sending notification to customer " + customerId + ": " + subject + " - " + message);
    }

    @Override
    public OrdersDataDto saveDataOrder(String customerId, BigDecimal amount) {
        Orders orders = new Orders(customerId, amount);
        orders = ordersRepository.save(orders);
        OrdersDataDto orderDataDto = new OrdersDataDto();

        orderDataDto.setId(orders.getId());
        orderDataDto.setCustomerId(orders.getCustomerId());
        orderDataDto.setAmount(orders.getAmount());
        orderDataDto.setStatus(orders.getStatus());
        orderDataDto.setCreatedAt(orders.getCreatedAt());
        return orderDataDto;
    }
}