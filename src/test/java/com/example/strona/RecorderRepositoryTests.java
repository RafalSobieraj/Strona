package com.example.strona;

import java.util.List;
import java.util.Optional;

import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.Recorder.RecorderRepository;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class RecorderRepositoryTests {

    @Autowired private RecorderRepository repository;

    @Before
    public void setUp(){
        Recorder recorder = new Recorder();
        recorder.setRecorderModel("Test");
        recorder.setRecorderType("Test Type");
        recorder.setCanalNumbers(2);
        recorder.setBandwidth(80);
        recorder.setPrice(100.0);
        repository.save(recorder);
    }

    @Test
    public void addNewRecorder(){
        Recorder recorder = new Recorder();
        recorder.setRecorderModel("Test Recorder");
        recorder.setRecorderType("Hybryd");
        recorder.setCanalNumbers(4);
        recorder.setBandwidth(60);
        recorder.setPrice(100.0);

        Recorder savedRecorder = repository.save(recorder);

        Assertions.assertThat(savedRecorder).isNotNull();
        Assertions.assertThat(savedRecorder.getId()).isGreaterThan(0);
    }

    @Test
    public void testFindAll(){
        Iterable<Recorder> recorders = repository.findAll();

        Assertions.assertThat(recorders).hasSizeGreaterThan(0);

    }

    @Test
    public void testUpdate(){
        List<Recorder> recorders = (List<Recorder>) repository.findAll();
        Optional<Recorder> optionalRecorder = Optional.ofNullable(recorders.get(0));
        Recorder recorder = new Recorder();
        if (optionalRecorder.isPresent()) {
            recorder = optionalRecorder.get();
            recorder.setCanalNumbers(2);
            repository.save(recorder);
        }

        Recorder updatedRecorder = repository.findById(recorder.getId()).get();
        Assertions.assertThat(updatedRecorder.getCanalNumbers()).isEqualTo(2);

    }

}