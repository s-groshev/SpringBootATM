package com.example.server.service;

import com.example.Response;
import com.example.server.dto.CardDTO;
import com.example.server.entities.Card;
import com.example.server.exception.CardNotFoundException;
import com.example.server.repository.CardRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.status.StatusRequest.*;

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
            return new Response(OK,card.getBalance(),card.getCurrency());
        else return new Response(WRONG_PASSWORD,null,null);
    }

    @Transactional
    public Response deposit(long id, int pin, BigDecimal amount){
        Card card=cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
        if(card.getPin()!=pin)
            return new Response(WRONG_PASSWORD,null,null);
        card.setBalance(card.getBalance().add(amount));
        card=cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
        return new Response(OK,card.getBalance(),card.getCurrency());
    }

    @Transactional
    public Response withdraw(long id, int pin, BigDecimal amount){
        Card card=cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
        if(card.getPin()!=pin)
            return new Response(WRONG_PASSWORD,null,null);
        if(card.getBalance().compareTo(amount)<0)
            return new Response(INSUFFICIENT_FUNDS,null,null);
        else card.setBalance(card.getBalance().subtract(amount));
        cardRepository.save(card);
        card=cardRepository.findById(id)
                .orElseThrow(CardNotFoundException::new);
        return new Response(OK,card.getBalance(),card.getCurrency());
    }
}
