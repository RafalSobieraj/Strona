package com.example.strona.model.camera;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CameraRepository extends CrudRepository<Camera, Integer> {
    Long countById(Integer id);

    @Query("SELECT c FROM Camera c WHERE CAST(c.id AS string) LIKE CONCAT('%',:query,'%') OR c.cameraModel LIKE CONCAT('%',:query,'%')")
    List<Camera> searchByIdOrName(String query);

}
