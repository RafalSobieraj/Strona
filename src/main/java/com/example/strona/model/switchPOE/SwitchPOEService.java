package com.example.strona.model.switchPOE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class SwitchPOEService {
    
    private final SwitchPOERepository repository;

    public SwitchPOEService(SwitchPOERepository repository) {
        this.repository = repository;
    }

    public List<SwitchPOE> listSwitches(){
        return (List<SwitchPOE>) repository.findAll();
    }

    public SwitchPOE save(SwitchPOE switchPOE) {
        return repository.save(switchPOE);
    }

    public SwitchPOE get(Integer id) throws NotFoundException {
        Optional<SwitchPOE> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any switches with ID " + id);
    }

    public List<SwitchPOE> searchByIdOrName(String query) {
        return new ArrayList<>(repository.searchByIdOrName(query));
    }

    public void delete(Integer id) throws NotFoundException{
        Long count = repository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find and objects with ID " + id);
        }
        repository.deleteById(id);
    }

}
