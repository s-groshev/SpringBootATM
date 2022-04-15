package com.example.dto;

import com.example.status.StatusRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BalanceDTO {
    StatusRequest statusRequest;
    BigDecimal balance;
    String currency;
}
