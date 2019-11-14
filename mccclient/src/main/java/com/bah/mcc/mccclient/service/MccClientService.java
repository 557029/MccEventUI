package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;
import com.bah.mcc.mccclient.dataaccess.Token;
import org.springframework.http.ResponseEntity;

public interface MccClientService {
    ResponseEntity<Token> getToken(MccCustomerDTO customerDTO);
    
}
