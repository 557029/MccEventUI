package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;
import com.bah.mcc.mccclient.dataaccess.Token;

public interface MccCustomerService {
    MccCustomerDTO getCustomer(Token accessToken, String username);
}
