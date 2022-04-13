package com.example;

import java.math.BigDecimal;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Response {
    BigDecimal balance;
    String currency;
}
