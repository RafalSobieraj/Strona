package com.example.strona.api;

import com.example.strona.model.Camera;
import com.example.strona.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CameraController {

    private final CameraService cameraService;

    @Autowired
    public CameraController(CameraService cameraService){
        this.cameraService = cameraService;
    }

    @PostMapping
    public void addCamera(Camera camera){
        cameraService.addCamera(camera);
    }
}
