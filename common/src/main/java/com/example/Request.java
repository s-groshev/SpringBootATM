package com.example;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Request {
    private long atmId;
    private long cardId;
    private int pin;
}
