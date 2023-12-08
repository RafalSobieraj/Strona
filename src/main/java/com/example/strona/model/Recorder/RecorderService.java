package com.example.strona.model.Recorder;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class RecorderService {

    private final RecorderRepository repository;

    public RecorderService(RecorderRepository repository) {
        this.repository = repository;
    }


    public List<Recorder> listRecorders(){
        return (List<Recorder>) repository.findAll();
    }

    public Recorder save(Recorder recorder) {
        return repository.save(recorder);
    }

    public List<Recorder> getByNameOrId(String query){
        List<Recorder> result = repository.findByNameOrId(query);
        if (result.size() != 0)
            return result;
        return Collections.emptyList();
    }

    public Recorder get(Integer id) throws NotFoundException {
        Optional<Recorder> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any recorders with ID " + id);
    }

    public void delete(Integer id) throws NotFoundException{
        Long count = repository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find and objects with ID " + id);
        }
        repository.deleteById(id);
    }

}
