package com.example.strona;

import java.util.List;

import javax.naming.Binding;

import java.util.ArrayList;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;


import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.Recorder.RecorderRepository;

import com.example.strona.model.switchPOE.SwitchPOE;
import com.example.strona.model.switchPOE.SwitchPOERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javassist.NotFoundException;

@Controller
public class ConfigurationController {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private RecorderRepository recorderRepository;
    @Autowired
    private SwitchPOERepository switchPOERepository;


    List<Camera> cameraList;
    List<Recorder> recorderList;
    List<SwitchPOE> switchList;

    @ModelAttribute
    public void getObjects(Model model){
        cameraList = (List<Camera>) cameraRepository.findAll();
        recorderList = (List<Recorder>) recorderRepository.findAll();
        switchList = (List<SwitchPOE>) switchPOERepository.findAll();
    }


    @GetMapping("/configuration")
    public String getCameraArray(Model model) {
        model.addAttribute("cameraList", cameraList);
        model.addAttribute("recorderList", recorderList);   
        model.addAttribute("switchList", switchList);
        return "configuration";
    }

    @PostMapping("/configuration/result")
    public String configurationResult(@Validated @ModelAttribute("recorderDropdown") Recorder recorder,
     Model model) throws NotFoundException
    {   


        if(recorder.getBandwidth() == 18)
            model.addAttribute("option1", "Wybrałeś odpowiednią liczbę kanałów.");
        else
            model.addAttribute("option1", "Wybrałeś zbyt małą liczbę kanałów.");
        return "configuration_result";
    }
}
