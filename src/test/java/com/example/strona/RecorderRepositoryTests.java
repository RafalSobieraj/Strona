package com.example.strona;

import java.util.Optional;

import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.Recorder.RecorderRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecorderRepositoryTests {

    @Autowired private RecorderRepository repository;

    @Test
    public void addNewrecorder(){
        Recorder recorder = new Recorder();
        recorder.setRecorderModel("Test Recorder");
        recorder.setRecorderType("Hybryd");
        recorder.setCanalNumbers(4);
        recorder.setBandwidth(60);

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
        Integer recorderId = 155;
        Optional<Recorder> optionalRecorder = repository.findById(recorderId);
        if (optionalRecorder.isPresent()) {
            Recorder recorder = optionalRecorder.get();
            recorder.setCanalNumbers(2);
            repository.save(recorder);
        }

        Recorder updatedRecorder = repository.findById(recorderId).get();
        Assertions.assertThat(updatedRecorder.getCanalNumbers()).isEqualTo(2);

    }

}