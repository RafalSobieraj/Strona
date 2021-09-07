package com.example.strona.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Camera {

    private final UUID CameraID;
    private final String CameraModel;
    private final String CameraType;
    private final int CameraResolution;

    public Camera(@JsonProperty("id") UUID cameraID,
                  @JsonProperty("model") String cameraModel,
                  @JsonProperty("type") String cameraType,
                  @JsonProperty("resolution") int cameraResolution) {
        this.CameraID = cameraID;
        this.CameraModel = cameraModel;
        this.CameraResolution = cameraResolution;
        this.CameraType = cameraType;
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
