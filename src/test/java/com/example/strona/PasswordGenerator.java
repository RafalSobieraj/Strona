package com.example.strona;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "configuration";
        String encoded = encoder.encode(rawPassword);

        System.out.println(encoded);
    }
    
}
