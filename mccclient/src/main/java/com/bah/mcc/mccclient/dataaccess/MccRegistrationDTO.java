package com.bah.mcc.mccclient.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MccRegistrationDTO {
    private Long customerId;
    private Long eventId;
    private Date dateRegistration;
    private int ticketNum;
}
