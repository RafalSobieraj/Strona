package com.example.strona.model.camera;

import javax.persistence.*;

@Entity
@Table(name = "cameras")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, name = "camera_model")
    private  String CameraModel;

    @Column(nullable = false, name = "camera_type")
    private String CameraType;

    @Column(nullable = false, name = "resolution")
    private int CameraResolution;

    @Column(nullable = true)
    private String Image;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    private boolean enabled;

    public Camera(Integer id,
                  String cameraModel,
                  String cameraType,
                  int cameraResolution) {
        this.id = id;
        this.CameraModel = cameraModel;
        this.CameraResolution = cameraResolution;
        this.CameraType = cameraType;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Camera{" +
                "CameraID=" + id +
                ", CameraModel='" + CameraModel + '\'' +
                ", CameraType='" + CameraType + '\'' +
                ", CameraResolution=" + CameraResolution +
                '}';
    }
}
