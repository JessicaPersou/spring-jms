package com.persou.springjms.configs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import jakarta.jms.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class JmsConfig {

    @Bean
    public SQSConnectionFactory sqsConnectionFactory(SqsClient sqsClient) {
        return new SQSConnectionFactory(new ProviderConfiguration(), sqsClient);
    }

    @Bean
    public JmsTemplate jmsTemplate(SQSConnectionFactory sqsConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(sqsConnectionFactory);
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(SQSConnectionFactory sqsConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setSessionTransacted(false);
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);

        // Para logs de erro mais detalhados
        factory.setErrorHandler(t -> {
            System.err.println("=== ERRO NO LISTENER ===");
            t.printStackTrace();
        });

        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}

