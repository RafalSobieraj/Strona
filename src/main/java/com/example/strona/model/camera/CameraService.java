package com.example.strona.model.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CameraService {

    @Autowired private CameraRepository repository;

    public List<Camera> listCameras(){
        return (List<Camera>) repository.findAll();
    }
}
