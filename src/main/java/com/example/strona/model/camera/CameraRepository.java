package com.example.strona.model.camera;

import org.springframework.data.repository.CrudRepository;

public interface CameraRepository extends CrudRepository<Camera, Integer> {
    public Long countById(Integer id);
}
