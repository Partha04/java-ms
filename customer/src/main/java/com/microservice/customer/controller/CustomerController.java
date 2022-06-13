package com.microservice.customer.controller;

import com.microservice.customer.events.CustomerRegistrationEvent;
import com.microservice.customer.model.Customer;
import com.microservice.customer.model.dto.CustomerRegisterDTO;
import com.microservice.customer.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegisterDTO customerDTO, final HttpServletRequest httpServletRequest){
        Customer registeredCustomer = customerService.registerCustomer(customerDTO);
        publisher.publishEvent(new CustomerRegistrationEvent(registeredCustomer,applicationUrl(httpServletRequest)));
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    private String applicationUrl(HttpServletRequest httpServletRequest) {
        return "http://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+httpServletRequest.getContextPath();
    }


}
