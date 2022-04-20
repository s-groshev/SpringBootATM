package com.example.service;

import com.example.Request;
import com.example.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static com.example.operation.Operation.*;

@Service
@AllArgsConstructor
public class ATMService {
    private final String HOST_URL = "http://localhost:8080/hosts/1/";
    private RestTemplate restTemplate;

    public Response getBalance(long atmId,long clientId,long cardId,int pin){
        HttpEntity<Request> requestEntity=new HttpEntity<>(new Request(atmId,cardId,pin, GET,null));
        HttpEntity<Response> responseEntity=restTemplate.exchange(HOST_URL+"clients/"+clientId, HttpMethod.POST
        ,requestEntity,Response.class);
        return responseEntity.getBody();
    }

    public Response deposit(long atmId, long clientId, long cardId, int pin, BigDecimal amount){

        //положить наличные и проверить банкоматом подлинность денег

        HttpEntity<Request> requestEntity=new HttpEntity<>(new Request(atmId,cardId,pin,DEPOSIT,amount));
        HttpEntity<Response> responseEntity=restTemplate.exchange(HOST_URL+"clients/"+clientId, HttpMethod.PUT
                ,requestEntity,Response.class);

        //если статус не OK, позвоните на горячюю линию

        return responseEntity.getBody();
    }

    public Response withdraw(long atmId, long clientId, long cardId, int pin, BigDecimal amount){
        HttpEntity<Request> requestEntity=new HttpEntity<>(new Request(atmId,cardId,pin,WITHDRAW,amount));
        HttpEntity<Response> responseEntity=restTemplate.exchange(HOST_URL+"clients/"+clientId, HttpMethod.PUT
                ,requestEntity,Response.class);

        //выдать наличные если статус ОК

        return responseEntity.getBody();
    }

}
