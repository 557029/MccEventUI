package com.bah.mcc.mccclient.service;

import com.bah.mcc.mccclient.dataaccess.MccEventDTO;
import com.bah.mcc.mccclient.dataaccess.Token;

import java.util.List;

public interface MccEventService {
    List<MccEventDTO> getAllEvents(Token accessToken);
}
