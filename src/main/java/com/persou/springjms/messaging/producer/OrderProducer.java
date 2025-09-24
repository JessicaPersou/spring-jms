package com.persou.springjms.messaging.producer;

import com.persou.springjms.dto.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private final JmsTemplate jmsTemplate;

    public OrderProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Order send(Order order) {
        try {
            jmsTemplate.convertAndSend("order-queue", order);
            return order;
        } catch (Exception ex) {
            throw new RuntimeException("Falha ao enviar pedido: ", ex);
        }
    }
}
