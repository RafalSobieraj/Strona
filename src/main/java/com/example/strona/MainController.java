package com.example.strona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired private WebSecurityConfig webSecurityConfig;

    @GetMapping("/admin")
    public String adminPanel(){
        return "admin";
    }

    @GetMapping("")
    public String homePage(){
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }
}
