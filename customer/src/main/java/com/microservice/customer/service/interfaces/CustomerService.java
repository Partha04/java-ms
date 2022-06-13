package com.microservice.customer.service.interfaces;

import com.microservice.customer.model.Customer;
import com.microservice.customer.model.dto.CustomerRegisterDTO;

public interface CustomerService {
    Customer registerCustomer(CustomerRegisterDTO customerDTO);
    void saveVerificationToken(String token, Customer customer);
    String verifyCustomerRegistrationToken(String token);
}
