package com.example.strona.data;

import com.example.strona.model.Camera;

import java.util.UUID;

public interface CameraData {

    int insertCameras(UUID CameraID, Camera camera);

    default int addCamera(Camera camera){
        UUID id = UUID.randomUUID();
        return insertCameras(id, camera);
    }
}
