package com.example.server.service;

import com.example.Response;
import com.example.server.dto.CardDTO;
import com.example.server.entities.Card;
import com.example.server.exception.CardNotFoundException;
import com.example.server.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CardService {
    private CardRepository cardRepository;

    public CardDTO getCard(long id){
        Card card=cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
        CardDTO cardDTO=new CardDTO(card.getId(),card.getCard_number(),
                card.getPin(),card.getBalance(),card.getCurrency());
        return cardDTO;
    }

    public List<CardDTO> getAllCard(){
        Iterable<Card> cards=cardRepository.findAll();
        List<CardDTO> cardDTOList=new ArrayList<>();
        for (Card card:cards)
            cardDTOList.add(new CardDTO(card.getId(),card.getCard_number(),
                    card.getPin(),card.getBalance(),card.getCurrency()));
        return cardDTOList;
    }

    public Response getBalance(long id, int pin){
        Card card=cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
        if(card.getPin()==pin)
            return new Response(card.getBalance(),card.getCurrency());
        else return new Response(new BigDecimal("0"),"WRONG PIN");
    }
}
