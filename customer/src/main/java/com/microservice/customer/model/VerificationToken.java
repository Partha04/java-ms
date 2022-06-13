package com.microservice.customer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken {
    private static final Integer EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CUSTOMER_VERIFY_TOKEN"))
    private Customer customer;

    public VerificationToken(String token, Customer customer) {
        this.token = token;
        this.customer = customer;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    public VerificationToken(String token) {
        this.token = token;
        this.expirationTime = calculateExpirationTime(EXPIRATION_TIME);
    }

    private LocalDateTime calculateExpirationTime(Integer expirationTime) {
        return LocalDateTime.now().plus(expirationTime, ChronoUnit.MINUTES);
    }


}
