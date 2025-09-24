package com.persou.springjms.messaging.consumer;

import com.persou.springjms.dto.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @JmsListener(destination = "order-queue")
    public void consumer(Order order) {
        System.out.println("Pedido recebido: "
            + order.getId() + " | "
            + order.getName() + " | "
            + order.getValue());
    }
}
