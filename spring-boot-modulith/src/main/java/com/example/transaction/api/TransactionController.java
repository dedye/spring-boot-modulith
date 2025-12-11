package com.example.transaction.api;

import com.example.orders.spi.dto.OrdersDataDto;
import com.example.transaction.api.dto.CreateTransactionRequestDto;
import com.example.transaction.internal.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<OrdersDataDto> createTransaction(@RequestBody CreateTransactionRequestDto request) {
        OrdersDataDto orders = transactionService.createOrderTransaction(request.getCustomerId(), request.getAmount());
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}/complete")
    public ResponseEntity<Void> completeOrder(@PathVariable Long orderId) {
        transactionService.completeTransaction(orderId);
        return ResponseEntity.ok().build();
    }
}