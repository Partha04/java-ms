package com.microservice.notification.service;

import com.microservice.notification.model.Message;
import com.microservice.notification.util.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    @Autowired
    EmailSender emailSender;

    @RabbitListener(queues = "queue.register")
    private void receiveMessageA(Message message) {
        System.out.println(message.toString());
        emailSender.sendEmailTo(message.getTo(), message.getSubject(), message.getBody());
    }
}
