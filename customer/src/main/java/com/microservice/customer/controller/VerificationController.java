package com.microservice.customer.controller;

import com.microservice.customer.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/verifyRegistration")
    String verifyCustomerEmail(@RequestParam(name="token") String token)
    {
        return customerService.verifyCustomerRegistrationToken(token);

    }
}
