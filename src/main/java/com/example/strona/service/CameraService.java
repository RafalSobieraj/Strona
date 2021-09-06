package com.example.strona.service;

import com.example.strona.data.CameraData;
import com.example.strona.model.Camera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CameraService {

    private final CameraData cameraData;

    @Autowired
    public CameraService(@Qualifier("cameraData") CameraData cameraData){
        this.cameraData = cameraData;
    }

    public int addCamera(Camera camera){
        return cameraData.insertCameras(camera);
    }
}
