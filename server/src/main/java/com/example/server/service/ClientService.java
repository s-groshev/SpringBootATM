package com.example.server.service;

import com.example.server.entities.Client;
import com.example.server.exception.ClientNotFoundException;
import com.example.server.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {
    ClientRepository clientRepository;

    @Transactional
    public Client getClient(long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Transactional
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }
}
