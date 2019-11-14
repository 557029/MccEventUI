package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;
import com.bah.mcc.mccclient.dataaccess.Token;
import com.bah.mcc.mccclient.dataaccess.TokenRequestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MccClientServiceImpl implements MccClientService {
    @Value("${authservice.url}")
    private String authUrl;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<Token> getToken(MccCustomerDTO customerDTO) {
        final TokenRequestData requestData = new TokenRequestData(customerDTO.getUsername(), customerDTO.getPassword());
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<TokenRequestData> requestEntity = new HttpEntity<>(requestData, requestHeaders);

        ResponseEntity<Token> token = this.restTemplate.postForEntity(this.authUrl + "/login", requestData, Token.class);
        return token;
    }

}
