package com.example.strona;

import java.util.List;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;


import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.Recorder.RecorderRepository;

import com.example.strona.model.switchPOE.SwitchPOE;
import com.example.strona.model.switchPOE.SwitchPOERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @Validated @ModelAttribute("cameraDropdown") Camera camera,
    @Validated @ModelAttribute("switchDropdown") SwitchPOE switchPOE,
    Model model)
    {   
        if(recorder.getId() == null || camera.getId() == null || switchPOE.getId() == null)
            model.addAttribute("error", "BŁĄD! WYBRANO NIEPRAWIDŁOWĄ WARTOŚĆ!");
        else{
            model.addAttribute("option2", camera.toString());
            model.addAttribute("option3", switchPOE.toString());

            if(recorder.getBandwidth() == 16)
            model.addAttribute("option1", recorder.toString());
            else
            model.addAttribute("option1", "Wybrałeś inną liczbę kanałów.");
        }
        return "configuration_result";
        
    }
}
