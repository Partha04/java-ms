package com.microservice.customer.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String to;
    private String subject;
    private String body;
}
