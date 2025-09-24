package com.persou.springjms.controller;

import com.persou.springjms.messaging.producer.OrderProducer;
import com.persou.springjms.dto.Order;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public Order send(@RequestBody Order order) {
        return orderProducer.send(order);
    }
}
