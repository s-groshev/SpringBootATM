package com.example;

import java.math.BigDecimal;

import com.example.status.StatusRequest;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response {
    StatusRequest statusRequest;
    BigDecimal balance;
    String currency;
}
