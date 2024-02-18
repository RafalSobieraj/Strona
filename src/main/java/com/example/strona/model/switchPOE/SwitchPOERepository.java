package com.example.strona.model.switchPOE;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SwitchPOERepository extends CrudRepository<SwitchPOE, Integer>{
    Long countById(Integer id);

    @Query("SELECT s FROM SwitchPOE s WHERE CAST(s.id AS string) LIKE CONCAT('%',:query,'%') OR s.switchModel LIKE CONCAT('%',:query,'%')")
    List<SwitchPOE> searchByIdOrName(String query);

}
