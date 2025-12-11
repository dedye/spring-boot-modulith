//package com.example.order;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.modulith.test.ApplicationModuleTest;
//import org.springframework.modulith.test.Scenario;
//
//import java.math.BigDecimal;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ApplicationModuleTest
//@SpringBootTest
//class OrderServiceTests {
//
//    @Autowired
//    private OrderService orderService;
//
//    @Test
//    void shouldCreateOrderAndPublishEvent(Scenario scenario) {
//        scenario.stimulate(() -> {
//                return orderService.createOrder("customer123", BigDecimal.valueOf(100.00));
//            })
//            .andWaitForEventOfType(OrderCreatedEvent.class)
//            .toArriveAndVerify((event, order) -> {
//                assertThat(event.customerId()).isEqualTo("customer123");
//                assertThat(event.amount()).isEqualTo(BigDecimal.valueOf(100.00));
//                assertThat(order.getId()).isEqualTo(event.orderId());
//            });
//    }
//
//    //@Test
//    /*void shouldCompleteOrderAndPublishEvent(Scenario scenario) {
//        Order order = orderService.createOrder("customer123", BigDecimal.valueOf(100.00));
//
//        scenario.stimulate(() -> {
//                orderService.completeOrder(order.getId());
//            })
//            .andWaitForEventOfType(OrderCompletedEvent.class)
//            .toArriveAndVerify(event -> {
//                assertThat(event.orderId()).isEqualTo(order.getId());
//                assertThat(event.customerId()).isEqualTo("customer123");
//            });
//    }*/
//}