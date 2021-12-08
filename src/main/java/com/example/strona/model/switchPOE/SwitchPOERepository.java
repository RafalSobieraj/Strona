package com.example.strona.model.switchPOE;

import org.springframework.data.repository.CrudRepository;

public interface SwitchPOERepository extends CrudRepository<SwitchPOE, Integer>{
    public Long countById(Integer id);
}
