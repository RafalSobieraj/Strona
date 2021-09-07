package com.example.strona.api;

import com.example.strona.model.Camera;
import com.example.strona.service.CameraService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/person")
@RestController
public class CameraController {

    private final CameraService cameraService;

    @Autowired
    public CameraController(CameraService cameraService){
        this.cameraService = cameraService;
    }

    @PostMapping
    public void addCamera(@RequestBody Camera camera){
        cameraService.addCamera(camera);
    }

    @GetMapping
    public ArrayList<Camera> getAllCamera(){
        return cameraService.getAllCamera();
    }
}
