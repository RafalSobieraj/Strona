package com.example.strona;

import java.util.ArrayList;
import java.util.List;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired private static CameraService cameraService;
    
    static List<Camera> cameraList = null;

    static{
        cameraList = new ArrayList<Camera>();
        cameraList.addAll(cameraService.listCameras());
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

    @GetMapping("/configuration")
    public String getCameraArray(Model model){
        model.addAttribute("cameraList", cameraList);
        return "configuration";
    }
}
