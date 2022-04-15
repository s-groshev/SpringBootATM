package com.example.server.service;

import com.example.server.dto.CardDTO;
import com.example.server.dto.ClientDTO;
import com.example.server.entities.Card;
import com.example.server.entities.Client;
import com.example.server.exception.ClientNotFoundException;
import com.example.server.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClientService {
    ClientRepository clientRepository;

    public ClientDTO getClient(long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
        Set<Card> cardSet = client.getCards();
        Set<CardDTO> cardDTOSet = new HashSet<>();

        for (Card card : cardSet)
            cardDTOSet.add(new CardDTO(card.getId(),card.getCard_number(),card.getPin()
                    ,card.getBalance(), card.getCurrency()));

        return new ClientDTO(client.getId(), client.getFirst_name(),
                client.getLast_name(), cardDTOSet);
    }
    public List<ClientDTO> getAllClients() {
        Iterable<Client> clientIterable = clientRepository.findAll();
        List<ClientDTO> clients = new ArrayList<>();

        clientIterable.forEach(
                client -> {
                    Set<Card> cardSet = client.getCards();
                    Set<CardDTO> cardDTOSet = new HashSet<>();

                    for (Card card : cardSet)
                        cardDTOSet.add(new CardDTO(card.getId(), card.getCard_number(), card.getPin()
                                , card.getBalance(), card.getCurrency()));

                    clients.add(new ClientDTO(client.getId(), client.getFirst_name(),
                            client.getLast_name(), cardDTOSet));
                }
        );
        return clients;
    }
}
