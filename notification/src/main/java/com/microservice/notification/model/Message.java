package com.microservice.notification.model;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private long id;
    private String to;
    private String subject;
    private String body;
}
