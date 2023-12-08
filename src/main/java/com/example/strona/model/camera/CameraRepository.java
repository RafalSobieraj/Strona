package com.example.strona.model.camera;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CameraRepository extends CrudRepository<Camera, Integer> {
    Long countById(Integer id);

    @Query("SELECT camera FROM Camera camera WHERE CAST(COALESCE(:query, '-1') AS string) = CAST(COALESCE(CAST(camera.id AS string), '-1') AS string) OR camera.cameraModel = :query")
    public List<Camera> findByNameOrId(@Param("query") String query);
}
