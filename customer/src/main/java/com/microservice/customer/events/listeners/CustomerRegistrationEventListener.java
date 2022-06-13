package com.microservice.customer.events.listeners;

import com.microservice.customer.events.CustomerRegistrationEvent;
import com.microservice.customer.model.Customer;
import com.microservice.customer.model.Message;
import com.microservice.customer.service.interfaces.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CustomerRegistrationEventListener implements ApplicationListener<CustomerRegistrationEvent> {
    @Autowired
    private CustomerService customerService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange directExchange;

    @Override
    public void onApplicationEvent(CustomerRegistrationEvent event) {
        ///create verification token of user with link
        System.out.println("Event is listened");
        //send email via notification
        Customer customer = event.getCustomer();
        String token = UUID.randomUUID().toString();
        customerService.saveVerificationToken(token, customer);
        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
        Message message = new Message(customer.getEmail(), "Verify email", "Please verify your email by clicking into this link " + url);
        //send email via notification service queue
        log.info("click link to verify your account-{}", url);
        log.info("Sending message to rabbitmq");
        rabbitTemplate.convertAndSend(directExchange.getName(), "routing.register", message);
        log.info("message sent");
    }
}
