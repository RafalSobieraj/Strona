package com.example.strona.model.camera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CameraService {

    @Autowired private CameraRepository repository;

    public List<Camera> listCameras(){
        return (List<Camera>) repository.findAll();
    }

    public Camera save(Camera camera) {
       return repository.save(camera);
    }

    public Camera get(Integer id) throws NotFoundException{
        Optional<Camera> result = repository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new NotFoundException("Could not find any cameras with ID " + id);
    }

    public void delete(Integer id) throws NotFoundException{
        Long count = repository.countById(id);
        if(count == null || count == 0){
            throw new NotFoundException("Could not find and objects with ID " + id);
        }
        repository.deleteById(id);
    }

    public void cleanDirectory(Path path) throws IOException {
        
        try {
            Files.list(path).forEach(file -> {
                if(!Files.isDirectory(file)) {
                    try {
                        Files.delete(file);
                    }catch(IOException ex) {    
                        try {
                            throw new IOException("Could not delete file: " + file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }catch(IOException e) {
            throw new IOException("Could not list directory: " + path);
        
        }
    }
}
