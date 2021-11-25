package com.example.strona;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String homePage(){
        return "index";
    }
    @GetMapping("/403")
    public String error403(){
        return "403";
    }
    @GetMapping("/adminLogin")
    public String adminLoginPage(){
        return "adminLogin";
    }

}
