package com.example.server.controller;

import com.example.Request;
import com.example.Response;
import com.example.server.dto.CardDTO;
import com.example.server.dto.ClientDTO;
import com.example.server.exception.HostNotFoundException;
import com.example.server.service.CardService;
import com.example.server.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class HostRestController {
    private final long HOST_ID=1;
    private CardService cardService;
    private ClientService clientService;

    @GetMapping("/hosts")
    public String getHostsInfo() {
        return "{data: \""+HOST_ID+" host available\"}";
    }

    @GetMapping("/hosts/{hostId}")
    public String getHostInfo(@PathVariable Long hostId) {
        if (hostId == HOST_ID) {
            return "{data: \"Host " + hostId + " ready\"}";
        }else{
            return "{data: \"Host " + hostId + " not ready\"}";
        }
    }

    @GetMapping("/hosts/{hostId}/clients")
    public List<ClientDTO> getClientsInfo(@PathVariable Long hostId) {
        if (hostId != HOST_ID) {
            throw new HostNotFoundException();
        }
        return clientService.getAllClients();
    }

    @GetMapping("/hosts/{hostId}/cards")
    public List<CardDTO> getCardsInfo(@PathVariable Long hostId) {
        if (hostId != HOST_ID) {
            throw new HostNotFoundException();
        }
        return cardService.getAllCard();
    }

    @PostMapping("/hosts/{hostId}/clients/{clientId}")
    public Response getBalance(@PathVariable("hostId") Long hostId,
                               @PathVariable("clientId") Long clientId,
                               @RequestBody Request request) {
        if (hostId != HOST_ID) {
            throw new RuntimeException("Host " + hostId + " is not ready!");
        }

        Response response = cardService.getBalance(request.getCardId(), request.getPin());
        return response;
    }
}
