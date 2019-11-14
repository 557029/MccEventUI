package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccEventDTO;
import com.bah.mcc.mccclient.dataaccess.Token;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service
public class MccEventServiceImpl implements MccEventService {
    @Value("${authservice.url}")
    private String authUrl;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private class ListEvents extends ArrayList<MccEventDTO>{}

    @Override
    public List<MccEventDTO> getAllEvents(Token accessToken) {
        if(accessToken == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken.getToken());

        ResponseEntity<String> responseEntity = this.restTemplate.exchange(this.authUrl + "/listevents",
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }
        Gson gson = new Gson();

        final String json = responseEntity.getBody();
        List<MccEventDTO> list = gson.fromJson(json, new TypeToken<List<MccEventDTO>>(){}.getType());
        return list;
    }
}
