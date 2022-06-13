package com.microservice.notification.configuration;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfiguration {

    @Bean
    Queue queueA() {
        return new Queue("queue.register", false);
    }


    @Bean
    DirectExchange exchangeDirect() {
        return new DirectExchange("exchange.direct");
    }


    //DirectExchange
    @Bean
    Binding bindingRegister(Queue queueA, DirectExchange exchange) {
        return BindingBuilder.bind(queueA).to(exchange).with("routing.register");
    }


    @Bean
    MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
