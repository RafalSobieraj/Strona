package com.example.strona;

import java.util.List;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;


import com.example.strona.model.recorder.Recorder;
import com.example.strona.model.recorder.RecorderRepository;
import com.example.strona.model.switchPOE.SwitchPOE;
import com.example.strona.model.switchPOE.SwitchPOERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String configurationResult(Model model, @ModelAttribute(name = "camera") Camera camera,
                                      @ModelAttribute(name = "recorder") Recorder recorder,
                                      @ModelAttribute(name = "switchPOE") SwitchPOE switchPOE){
        return "configuration_result";
    }
}
