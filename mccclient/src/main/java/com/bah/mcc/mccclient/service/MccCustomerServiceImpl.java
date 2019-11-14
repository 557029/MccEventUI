package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;
import org.springframework.beans.factory.annotation.Value;

public class MccCustomerServiceImpl implements MccCustomerService {
    @Value("${authservice.url}")
    private String authUrl;

    @Override
    public MccCustomerDTO getCustomer(String username) {
        return null;
    }
}
