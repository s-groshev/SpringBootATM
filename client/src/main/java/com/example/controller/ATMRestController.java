package com.example.controller;

import com.example.Response;
import com.example.exception.ATMInternalErrorException;
import com.example.service.ATMService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
public class ATMRestController {
    private final long ATM_ID=1;
    private ATMService atmService;

    @GetMapping("/ATMs")
    public String getATMsStatus() {
        return "1 ATM is ready.";
    }

    @GetMapping("/ATMs/{ATMId}/clients/{clientId}/cards/{cardId}/{pin}")
    public Response getClientBalance(
            @PathVariable("ATMId") Long ATMId,
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("pin") int pin) {

        if (ATMId != ATM_ID) {
            throw new ATMInternalErrorException("ATM internal Error");
        }

        return atmService.getBalance(ATMId,clientId,cardId,pin);
    }

    @PutMapping("/ATMs/{ATMId}/clients/{clientId}/cards/{cardId}/{pin}/deposit/{amount}")
    public Response depositClientBalance(
            @PathVariable("ATMId") Long ATMId,
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("pin") int pin,
            @PathVariable("amount") BigDecimal amount)
        {

        if (ATMId != ATM_ID) {
            throw new ATMInternalErrorException("ATM internal Error");
        }

        return atmService.deposit(ATMId,clientId,cardId,pin,amount);
    }

    @PutMapping("/ATMs/{ATMId}/clients/{clientId}/cards/{cardId}/{pin}/withdraw/{amount}")
    public Response withdrawClientBalance(
            @PathVariable("ATMId") Long ATMId,
            @PathVariable("clientId") Long clientId,
            @PathVariable("cardId") Long cardId,
            @PathVariable("pin") int pin,
            @PathVariable("amount") BigDecimal amount)
    {

        if (ATMId != ATM_ID) {
            throw new ATMInternalErrorException("ATM internal Error");
        }

        return atmService.withdraw(ATMId,clientId,cardId,pin,amount);
    }

}
