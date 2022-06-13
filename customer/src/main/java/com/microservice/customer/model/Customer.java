package com.microservice.customer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor@Getter
@Setter
@Table(name="CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String email;
    @Column(length = 60)
    private String password;
    private String role;
    private boolean enabled=false;
}
