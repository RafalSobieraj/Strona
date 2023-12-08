package com.example.strona.model.Recorder;

import com.example.strona.model.camera.Camera;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecorderRepository extends CrudRepository<Recorder, Integer> {
    public Long countById(Integer id);

    @Query("SELECT recorder FROM Recorder recorder WHERE CAST(COALESCE(:query, '-1') AS string) = CAST(COALESCE(CAST(recorder.id AS string), '-1') AS string) OR recorder.recorderModel = :query")
    public List<Recorder> findByNameOrId(@Param("query") String query);
}

