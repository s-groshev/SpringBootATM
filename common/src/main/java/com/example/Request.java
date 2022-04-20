package com.example;

import com.example.operation.Operation;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Request {
    private long atmId;
    private long cardId;
    private int pin;
    private Operation operation;
    private BigDecimal amount;
}
