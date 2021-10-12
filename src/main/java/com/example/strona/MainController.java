package com.example.strona;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/admin")
    public String adminPanel(){
        return "admin";
    }

    @GetMapping("")
    public String homePage(){
        return "index";
    }
}
