package com.microservice.customer.model.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String confirmPassword;
}
