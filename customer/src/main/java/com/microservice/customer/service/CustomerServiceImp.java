package com.microservice.customer.service;

import com.microservice.customer.model.Customer;
import com.microservice.customer.model.VerificationToken;
import com.microservice.customer.model.dto.CustomerRegisterDTO;
import com.microservice.customer.repository.CustomerRepository;
import com.microservice.customer.repository.VerificationTokenRepository;
import com.microservice.customer.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImp  implements CustomerService {
    private static final String EMAIL_EXISTS = "Email already exists";

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public CustomerServiceImp(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer registerCustomer(CustomerRegisterDTO customerDTO) {

        //todo:check if the customer is fraud
        Customer existingCustomer=customerRepository.findByemail(customerDTO.getEmail());
        if(existingCustomer!=null)
        {
            throw new RuntimeException(EMAIL_EXISTS);
        }
        Customer customer = Customer.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .password(passwordEncoder.encode(customerDTO.getPassword()))
                .role("user")
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public void saveVerificationToken(String token, Customer customer) {
        VerificationToken verificationToken=new VerificationToken(token,customer);
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String verifyCustomerRegistrationToken(String token) {
        VerificationToken verificationToken=verificationTokenRepository.findByToken(token);
        if(verificationToken==null)
        {
            return "Invalid Token";
        }
        Customer customer=verificationToken.getCustomer();
        LocalDateTime expirationTime = verificationToken.getExpirationTime();
        int compare = expirationTime.compareTo(LocalDateTime.now());
        if(compare<0)
        {
            customerRepository.delete(customer);
            verificationTokenRepository.delete(verificationToken);
            return "Token has expired,try registering again";
        }
        customer.setEnabled(true);
        customerRepository.save(customer);
        return "Email is verified";
    }
}
