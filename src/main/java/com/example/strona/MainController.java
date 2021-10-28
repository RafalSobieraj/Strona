package com.example.strona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired private WebSecurityConfig webSecurityConfig;

    @GetMapping("/admin")
    public String adminPanel(){
        if(webSecurityConfig.isAuthenticated()){
            return "admin";
        }
        return "adminLogin";
    }

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
