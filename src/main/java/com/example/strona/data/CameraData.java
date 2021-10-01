package com.example.strona.data;

import com.example.strona.model.Camera;

import java.util.ArrayList;
import java.util.UUID;

public interface CameraData {

    int insertCameras(Integer CameraID, Camera camera);

    default int insertCameras(Camera camera){
        Integer id = camera.getCameraID();
        return insertCameras(id, camera);
    }

    ArrayList<Camera> selectAllCamera();
}
