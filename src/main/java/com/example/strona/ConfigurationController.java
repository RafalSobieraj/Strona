package com.example.strona;

import java.util.ArrayList;
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



    @GetMapping("/configuration")
    public String getCameraArray(Model model) {
        List<Camera> cameraList = (List<Camera>) cameraRepository.findAll();
        model.addAttribute("cameraList", cameraList);
        model.addAttribute("cameras", new Camera());
        List<Recorder> recorderList = (List<Recorder>) recorderRepository.findAll();
        model.addAttribute("recorderList", recorderList);
        model.addAttribute("recorders", new Recorder());
        List<SwitchPOE> switchList = (List<SwitchPOE>) switchPOERepository.findAll();
        model.addAttribute("switchList", switchList);
        model.addAttribute("switches", new SwitchPOE());
        return "configuration";
    }

    @PostMapping("/configuration/result")
    public String configurationResult(@ModelAttribute(name ="Recorder") Recorder recorder,
     Model model, RedirectAttributes re) throws NotFoundException
    {
        model.addAttribute("data", recorder);
        model.addAttribute("option1", "Wybrałeś następującą liczbę kanałów: ");
        return "configuration_result";
    }
}
