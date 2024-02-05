package com.example.strona;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CameraRepositoryTests {

    @Autowired private CameraRepository repository;

    @Before
    public void setUp(){
        Camera camera = new Camera();
        camera.setCameraModel("test");
        camera.setCameraResolution(123);
        camera.setCameraType("Test Type");
        camera.setPrice(100.0);
        repository.save(camera);
    }

    @Test
    public void addNewCamera(){
        Camera camera = new Camera();
        camera.setCameraModel("Test Camera2");
        camera.setCameraType("Hybryd");
        camera.setCameraResolution(3);
        camera.setPrice(100.0);

        Camera savedCamera = repository.save(camera);

        Assertions.assertThat(savedCamera).isNotNull();
        Assertions.assertThat(savedCamera.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAll(){

        Iterable<Camera> cameras = repository.findAll();

        Assertions.assertThat(cameras).hasSizeGreaterThan(0);

    }

    @Test
    public void testUpdate(){
        List<Camera> cameras = (List<Camera>) repository.findAll();
        Optional<Camera> optionalCamera = Optional.ofNullable(cameras.get(0));
        Camera camera = new Camera();
        if(optionalCamera.isPresent()) {
            camera = optionalCamera.get();
            camera.setCameraResolution(2);
            repository.save(camera);
        }

        Camera updatedCamera = repository.findById(camera.getId()).get();
        Assertions.assertThat(updatedCamera.getCameraResolution()).isEqualTo(2);

    }
}
