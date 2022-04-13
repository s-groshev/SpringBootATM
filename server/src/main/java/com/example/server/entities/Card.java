package com.example.server.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Card")
@NoArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long card_number;
    private int pin;
    private BigDecimal balance;
    private String currency;

    @ManyToOne
    @JoinColumn(name="client_id",nullable = false)
    private Client client;
}
