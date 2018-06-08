package com.answerdigital.thick.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	
	@Value("${patient.exchange.key}")
    private String patientExchangeKey;

	@Value("${patient.context.changed.key}")
    private String patientContextChangedKey;
	
    @Bean
    public List<Declarable> patientBindings() {
        return createBindings("patient-context-changed-queue", patientExchangeKey, patientContextChangedKey);
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    
	private List<Declarable> createBindings(String contextChangedQueueName, String exchangeKey, String contextChangedKey) {
		Queue contextChangedQueue = QueueBuilder.durable(contextChangedQueueName).build();
        DirectExchange exchange = new DirectExchange(exchangeKey);
     
        return Arrays.asList(contextChangedQueue, exchange,
        		BindingBuilder.bind(contextChangedQueue).to(exchange).with(contextChangedKey));
	}
}
