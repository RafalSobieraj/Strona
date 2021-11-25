package com.example.strona;

import java.util.List;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfigurationController {

    @Autowired private CameraRepository cameraRepository;
    

    @GetMapping("/configuration")
    public String getCameraArray(Model model){
        List<Camera> cameraList = (List<Camera>) cameraRepository.findAll();
        model.addAttribute("cameraList", cameraList);
        model.addAttribute("cameras", new Camera());
        return "configuration";
    }
}
