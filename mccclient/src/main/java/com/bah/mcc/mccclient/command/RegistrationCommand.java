package com.bah.mcc.mccclient.command;

import com.bah.mcc.mccclient.dataaccess.MccRegistrationDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationCommand {
    MccRegistrationDTO registration;
}
