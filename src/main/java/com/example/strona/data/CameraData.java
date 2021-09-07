package com.example.strona.data;

import com.example.strona.model.Camera;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface CameraData {

    int insertCameras(UUID CameraID, Camera camera);

    default int insertCameras(Camera camera){
        UUID id = UUID.randomUUID();
        return insertCameras(id, camera);
    }

    ArrayList<Camera> selectAllCamera();
}
