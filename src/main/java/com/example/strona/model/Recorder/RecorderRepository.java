package com.example.strona.model.recorder;

import org.springframework.data.repository.CrudRepository;

public interface RecorderRepository extends CrudRepository<Recorder, Integer> {
    public Long countById(Integer id);
}

