package com.example.transaction.event;

import org.springframework.modulith.events.Externalized;

@Externalized("order.completed::#{customerId()}")
public record TransactionCompletedEvent(Long orderId) {}