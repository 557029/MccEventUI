package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;

public interface MccCustomerService {
    MccCustomerDTO getCustomer(String username);
}
