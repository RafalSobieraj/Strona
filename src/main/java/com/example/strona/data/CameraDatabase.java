package com.example.strona.data;

import com.example.strona.model.Camera;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("cameraData")
public class CameraDatabase implements CameraData{

    private static ArrayList<Camera> DB = new ArrayList<>();

    @Override
    public int insertCameras(UUID CameraID, Camera camera) {
        DB.add(new Camera(CameraID, camera.getCameraModel(), camera.getCameraType(), camera.getCameraResolution()));
        return 1;
    }

    @Override
    public ArrayList<Camera> selectAllCamera(){
        return DB;
    }
}
