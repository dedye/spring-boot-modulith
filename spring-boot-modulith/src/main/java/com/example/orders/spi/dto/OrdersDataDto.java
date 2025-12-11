package com.example.orders.spi.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrdersDataDto {
    private Long id;
    private String customerId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}
