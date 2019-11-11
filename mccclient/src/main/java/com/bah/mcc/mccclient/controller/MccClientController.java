package com.bah.mcc.mccclient.controller;

import com.bah.mcc.mccclient.dataaccess.MccEventDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class MccClientController {
    private String message;
    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "index"; //view
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);

        return "signup"; //view
    }

    @GetMapping("/events")
    public String events(Model model) {
        final List<MccEventDTO> list = new ArrayList<>();
        list.add(new MccEventDTO(Long.parseLong("1"), "Test1", new Date(),10,10));
        list.add(new MccEventDTO(Long.parseLong("2"), "Test2", new Date(),5,15));
        list.add(new MccEventDTO(Long.parseLong("3"), "Test3", new Date(),20,20));

        model.addAttribute("message", message);
        model.addAttribute("tasks", tasks);
        model.addAttribute("list", list);
        return "events"; //view
    }

}
