package com.example.transaction.internal;

import com.example.orders.spi.OrdersData;
import com.example.orders.spi.dto.OrdersDataDto;
import com.example.transaction.event.TransactionCompletedEvent;
import com.example.transaction.event.TransactionCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
public class TransactionService {
    private final OrdersData ordersData;
    private final ApplicationEventPublisher eventPublisher;

    public TransactionService(OrdersData ordersData, ApplicationEventPublisher eventPublisher) {
        // Spring injects the hidden InventoryServiceImpl here.
        this.ordersData = ordersData;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public OrdersDataDto createOrderTransaction(String customerId, BigDecimal amount) {
        OrdersDataDto orderDataDto = ordersData.saveDataOrder(customerId, amount);

        eventPublisher.publishEvent(new TransactionCreatedEvent(orderDataDto.getId(), customerId));

        if (orderDataDto.getCustomerId().isEmpty()) {
            throw new IllegalStateException("dataCustomer unavailable");
        } else {
            return orderDataDto;
        }
    }

    public void completeTransaction(Long orderId) {
        eventPublisher.publishEvent(new TransactionCompletedEvent(orderId));
    }
}