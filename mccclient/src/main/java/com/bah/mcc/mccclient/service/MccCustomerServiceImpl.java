package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;
import com.bah.mcc.mccclient.dataaccess.Token;
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

@Service
public class MccCustomerServiceImpl implements MccCustomerService {
    @Value("${authservice.url}")
    private String authUrl;
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MccCustomerDTO getCustomer(Token accessToken, String username) {
      if(accessToken == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken.getToken());

        ResponseEntity<MccCustomerDTO> responseEntity = this.restTemplate.exchange(this.authUrl + "/customer/" + username,
                HttpMethod.GET,
                new HttpEntity<MccCustomerDTO>(headers),
                MccCustomerDTO.class);
        if(!responseEntity.getStatusCode().equals(HttpStatus.OK)) {
            return null;
        }
        return responseEntity.getBody();

    }
}
