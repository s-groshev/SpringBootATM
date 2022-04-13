package com.example.server.dto;

import com.example.server.entities.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientDTO {
    private long id;
    private String first_name;
    private String last_name;
    private Set<CardDTO> cards;
}
