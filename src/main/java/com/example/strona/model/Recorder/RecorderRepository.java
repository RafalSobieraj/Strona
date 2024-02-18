package com.example.strona.model.Recorder;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecorderRepository extends CrudRepository<Recorder, Integer> {
    Long countById(Integer id);

    @Query("SELECT r FROM Recorder r WHERE CAST(r.id AS string) LIKE CONCAT('%',:query,'%') OR r.recorderModel LIKE CONCAT('%',:query,'%')")
    List<Recorder> searchByIdOrName(String query);
}

