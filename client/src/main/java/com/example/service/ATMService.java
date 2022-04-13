package com.example.service;

import com.example.Request;
import com.example.Response;
import com.example.dto.BalanceDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class ATMService {
    private final String HOST_URL = "http://localhost:8080/hosts/1/";
    private RestTemplate restTemplate;

    public BalanceDTO getBalance(long atmId,long clientId,long cardId,int pin){
        HttpEntity<Request> requestEntity=new HttpEntity<>(new Request(atmId,cardId,pin));
        HttpEntity<Response> responseEntity=restTemplate.exchange(HOST_URL+"clients/"+clientId, HttpMethod.POST
        ,requestEntity,Response.class);
        return new BalanceDTO(responseEntity.getBody().getBalance()
                ,responseEntity.getBody().getCurrency());
    }

    public String getAllClient(){
        HttpEntity<String> responseEntity=restTemplate.exchange(HOST_URL+"clients/", HttpMethod.GET
                ,null,String.class);
        return responseEntity.getBody();
    }

    public String getAllCards(){
        HttpEntity<String> responseEntity=restTemplate.exchange(HOST_URL+"cards/", HttpMethod.GET
                ,null,String.class);
        return responseEntity.getBody();
    }
}
