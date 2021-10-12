package com.example.strona.model.camera;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "cameras")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CameraID;

    @Column(nullable = false, unique = true, name = "camera_model")
    private  String CameraModel;

    @Column(nullable = false, name = "camera_type")
    private String CameraType;

    @Column(nullable = false, name = "resolution")
    private int CameraResolution;

    public Camera(Integer cameraID,
                  String cameraModel,
                  String cameraType,
                  int cameraResolution) {
        this.CameraID = cameraID;
        this.CameraModel = cameraModel;
        this.CameraResolution = cameraResolution;
        this.CameraType = cameraType;
    }

    public Camera() {

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

    public Integer getCameraID() {
        return CameraID;
    }

    public void setCameraID(Integer cameraID) {
        CameraID = cameraID;
    }

    public void setCameraModel(String cameraModel) {
        CameraModel = cameraModel;
    }

    public void setCameraType(String cameraType) {
        CameraType = cameraType;
    }

    public void setCameraResolution(int cameraResolution) {
        CameraResolution = cameraResolution;
    }
}
