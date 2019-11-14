package com.bah.mcc.mccclient.controller;

import com.bah.mcc.mccclient.command.EventCommand;
import com.bah.mcc.mccclient.command.LoginCommand;
import com.bah.mcc.mccclient.command.RegistrationCommand;
import com.bah.mcc.mccclient.dataaccess.MccCustomerDTO;
import com.bah.mcc.mccclient.dataaccess.MccEventDTO;
import com.bah.mcc.mccclient.dataaccess.Token;
import com.bah.mcc.mccclient.service.MccClientService;
import com.bah.mcc.mccclient.service.MccCustomerService;
import com.bah.mcc.mccclient.service.MccEventService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@Getter
@Setter
public class MccClientController {
    private MccClientService clientService;
    private MccEventService eventService;
    private MccCustomerService customerService;

    private LoginCommand loginCommand;
    private Token token;
    private MccCustomerDTO customer;

    @Autowired
    public void setClientService(MccClientService clientService) {
        this.clientService = clientService;
    }

    @Autowired
    public void setEventService(MccEventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setCustomerService(MccCustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String main(Model model) {
        LoginCommand loginCommand = new LoginCommand();
        model.addAttribute("loginCommand", loginCommand);
        return "index"; //view
    }

    @RequestMapping(value = "/gettoken", method = RequestMethod.POST)
    public String getToken(LoginCommand loginCommand, BindingResult bindingResult ) {
        MccCustomerDTO customerDTO = new MccCustomerDTO();
        customerDTO.setUsername(loginCommand.getUsername());
        customerDTO.setPassword(loginCommand.getPassword());
        ResponseEntity<Token> token = this.clientService.getToken(customerDTO);
        token.getStatusCode().equals(HttpStatus.OK);
        this.token = token.getBody();
        this.customer = customerDTO;
        return "redirect:/events";
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events(EventCommand evenCommand, Model model) {
        final List<MccEventDTO> list = this.eventService.getAllEvents(this.token);
        model.addAttribute("listevents", list);
        model.addAttribute("eventCommand", evenCommand);
        return "events"; //view
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void registratation(@ModelAttribute RegistrationCommand registrationCommand, Model model) {
        MccCustomerDTO customerDTO = this.customerService.getCustomer(this.token, this.customer.getUsername());
        model.addAttribute("customer", customerDTO);
        model.addAttribute("registrationCommand", registrationCommand);
    }



}
