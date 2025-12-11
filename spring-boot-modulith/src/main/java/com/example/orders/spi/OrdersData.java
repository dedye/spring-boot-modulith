package com.example.orders.spi;

import com.example.orders.spi.dto.OrdersDataDto;

import java.math.BigDecimal;

public interface OrdersData {
    OrdersDataDto saveDataOrder(String customerId, BigDecimal amount);
}