package com.example.strona.model;

import java.util.UUID;

public class Camera {

    private final UUID CameraID;
    private final String CameraModel;
    private final String CameraType;
    private final int CameraResolution;

    public Camera(UUID cameraID, String cameraModel, String cameraType, int cameraResolution) {
        CameraID = cameraID;
        CameraModel = cameraModel;
        CameraType = cameraType;
        CameraResolution = cameraResolution;
    }

    public String getCameraModel() {
        return CameraModel;
    }

    public String getCameraType() {
        return CameraType;
    }

    public int getCameraResolution() {
        return CameraResolution;
    }

    public UUID getCameraID() {
        return CameraID;
    }
}
