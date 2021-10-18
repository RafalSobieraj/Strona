package com.example.strona;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CameraRepositoryTests {

    @Autowired private CameraRepository repository;

    @Test
    public void addNewCamera(){
        Camera camera = new Camera();
        camera.setCameraModel("Test Camera2");
        camera.setCameraType("Hybryd");
        camera.setCameraResolution(3);

        Camera savedCamera = repository.save(camera);

        Assertions.assertThat(savedCamera).isNotNull();
        Assertions.assertThat(savedCamera.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAll(){
        Iterable<Camera> cameras = repository.findAll();
        Assertions.assertThat(cameras).hasSizeGreaterThan(0);

        for (Camera camera : cameras){
            System.out.println(camera);
        }

    }

    @Test
    public void testUpdate(){
        Integer cameraId = 5;
        Optional<Camera> optionalCamera = repository.findById(cameraId);
        Camera camera = optionalCamera.get();
        camera.setCameraResolution(2);
        repository.save(camera);

        Camera updatedCamera = repository.findById(cameraId).get();
        Assertions.assertThat(updatedCamera.getCameraResolution()).isEqualTo(2);

    }

}
