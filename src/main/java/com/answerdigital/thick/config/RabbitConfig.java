package com.answerdigital.thick.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class RabbitConfig implements RabbitListenerConfigurer {
	
	@Value("${patient.exchange.key}")
    private String patientExchangeKey;

	@Value("${patient.context.changed.key}")
    private String patientContextChangedKey;
	
	@Value("${patient.context.ended.key}")
    private String patientContextEndedKey;
	
    @Bean
    public List<Declarable> patientBindings() {
        return createBindings("patient-context-changed-queue", "patient-context-ended-queue", patientExchangeKey, patientContextChangedKey, patientContextEndedKey);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    
    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
       DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
       factory.setMessageConverter(new MappingJackson2MessageConverter());
       return factory;
    }
     
    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
       registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
    
	private List<Declarable> createBindings(String contextChangedQueueName, String contextEndedQueueName, String exchangeKey, String contextChangedKey, String contextEndedKey) {
		Queue contextChangedQueue = QueueBuilder.durable(contextChangedQueueName).build();
		Queue contextEndedQueue = QueueBuilder.durable(contextEndedQueueName).build();
        DirectExchange exchange = new DirectExchange(exchangeKey);
     
        return Arrays.asList(contextChangedQueue, contextEndedQueue, exchange,
        		BindingBuilder.bind(contextChangedQueue).to(exchange).with(contextChangedKey),
        		BindingBuilder.bind(contextEndedQueue).to(exchange).with(contextEndedKey));
	}
}
