package com.example.strona.controllers;

import com.example.strona.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

import java.sql.Date;
import java.time.Instant;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(TemplateInputException.class)
    public ModelAndView handleError(Model model) {
        ModelAndView modelAndView = new ModelAndView("404");
        Error error = Error.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message("ERROR 404: Page not found!")
                .timestamp(Date.from(Instant.now()))
                .build();
        model.addAttribute("error", error);
        return modelAndView;
    }

}
