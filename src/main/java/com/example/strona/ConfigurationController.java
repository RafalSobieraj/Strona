package com.example.strona;

import java.util.List;
import java.util.ArrayList;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;


import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.Recorder.RecorderRepository;
import com.example.strona.model.Recorder.RecorderService;
import com.example.strona.model.switchPOE.SwitchPOE;
import com.example.strona.model.switchPOE.SwitchPOERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;

@Controller
public class ConfigurationController {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private RecorderRepository recorderRepository;
    @Autowired
    private SwitchPOERepository switchPOERepository;
    private RecorderService recorderService;

    List<Camera> cameraList;
    List<Recorder> recorderList;
    List<SwitchPOE> switchList;

    @ModelAttribute
    public void getObjects(Model model){
     //   cameraList = (List<Camera>) cameraRepository.findAll();
        recorderList = (List<Recorder>) recorderRepository.findAll();
     //   switchList = (List<SwitchPOE>) switchPOERepository.findAll();
    }


    @GetMapping("/configuration")
    public String getCameraArray(Model model) {
     //   model.addAttribute("cameraList", cameraList);
        model.addAttribute("recorderList", recorderList);   
     //   model.addAttribute("switchList", switchList);
        return "configuration";
    }

    @PostMapping("/configuration/result")
    public String configurationResult(@ModelAttribute(name = "recorder") Recorder recorder,
     Model model) throws NotFoundException
    {   
        model.addAttribute("data", recorder.toString());
        model.addAttribute("option1", "Wybrałeś następującą liczbę kanałów: ");
        return "configuration_result";
    }
}
