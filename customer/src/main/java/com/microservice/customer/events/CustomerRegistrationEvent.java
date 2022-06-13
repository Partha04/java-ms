package com.microservice.customer.events;

import com.microservice.customer.model.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
public class CustomerRegistrationEvent  extends ApplicationEvent {
    private Customer customer;
    private String applicationUrl;

    public CustomerRegistrationEvent(Customer customer,String applicationUrl) {
        super(customer);
        this.customer=customer;
        this.applicationUrl=applicationUrl;
        log.info("Event is created");
    }
}
