package com.example.transaction.event;

import org.springframework.modulith.events.Externalized;

@Externalized("order.created::#{customerId()}")
public record TransactionCreatedEvent(Long orderId, String customerId) {}