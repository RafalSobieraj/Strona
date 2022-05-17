package com.example.strona.model.camera;

import javax.persistence.*;

@Entity
@Table(name = "cameras")
public class Camera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "camera_model")
    private  String CameraModel;

    @Column(nullable = false, name = "camera_type")
    private String CameraType;

    @Column(nullable = false, name = "resolution")
    private int CameraResolution;

    @Column(nullable = true)
    private String Image;

    @Column(nullable = true, name = "url")
    private String Link;


    public Camera(Integer id,
                  String cameraModel,
                  String cameraType,
                  int cameraResolution, String link) {
        super();
        this.id = id;
        this.CameraModel = cameraModel;
        this.CameraResolution = cameraResolution;
        this.CameraType = cameraType;
        this.Link = link;
    }

    public Camera() {
        super();
    }

    public String getLink() {
        return Link;
    }


    public void setLink(String link) {
        Link = link;
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

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Transient
    public String getImagePath(){
        if(Image == null || id == null) return null;

        return "/images/" + "cameras/" + id + "/" + Image;
    }

    @Override
    public String toString() {
        return " ROZDZIELCZOŚĆ = " + CameraResolution + " MPx" + ", TYP = " + CameraType;
    }

}
