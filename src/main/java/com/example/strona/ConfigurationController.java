package com.example.strona;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.strona.model.camera.Camera;
import com.example.strona.model.camera.CameraRepository;


import com.example.strona.model.Recorder.Recorder;
import com.example.strona.model.Recorder.RecorderRepository;

import com.example.strona.model.switchPOE.SwitchPOE;
import com.example.strona.model.switchPOE.SwitchPOERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConfigurationController {

    @Autowired
    private CameraRepository cameraRepository;
    @Autowired
    private RecorderRepository recorderRepository;
    @Autowired
    private SwitchPOERepository switchPOERepository;

    List<Camera> cameraList;
    List<Recorder> recorderList;
    List<SwitchPOE> switchList;

    @ModelAttribute
    public void getObjects(Model model){
        cameraList = (List<Camera>) cameraRepository.findAll();
        recorderList = (List<Recorder>) recorderRepository.findAll();
        switchList = (List<SwitchPOE>) switchPOERepository.findAll();
    }


    @GetMapping("/configuration")
    public String getCameraArray(Model model, Camera camera) {
        model.addAttribute("cameraList", cameraList);
        model.addAttribute("recorderList", recorderList);   
        model.addAttribute("switchList", switchList);
        return "configuration";
    }

    @PostMapping("/configuration/result")
    public String configurationResult(@Validated @ModelAttribute("recorderDropdown") Recorder recorder,
    @Validated @ModelAttribute("cameraDropdown") Camera camera,
    @Validated @ModelAttribute("switchDropdown") SwitchPOE switchPOE,
    Model model)
    {  

        if(recorder.getId() == null || camera.getId() == null || switchPOE.getId() == null)
            model.addAttribute("error", "BŁĄD! WYBRANO NIEPRAWIDŁOWĄ WARTOŚĆ!");
        else{
            List<Camera> resultCamera = cameraList.stream().filter(x -> Objects.equals(x.getCameraResolution(), 4))
            .collect(Collectors.toList());

            List<Recorder> resultRecorder = recorderList.stream().filter(x -> Objects.equals(x.getStorageLimit(), 6))
            .collect(Collectors.toList());

            List<SwitchPOE> resultSwitch = switchList.stream().filter(x -> Objects.equals(x.getPortSpeed(), 100))
            .collect(Collectors.toList());

            if(recorder.getRecorderType().equals("Hybryda"))
                model.addAttribute("conclusion", "");

            else if(recorder.getRecorderType().equals("Analog") == camera.getCameraType().equals("IP") || recorder.getRecorderType().equals("IP") == camera.getCameraType().equals("Analog"))
                model.addAttribute("conclusion", "Wybrana konfiguracja nie jest prawidłowa, ponieważ typ kamery nie jest taki sam jak" +
                " typ rejestratora.  Proszę wybrać poprawny typ urządzeń lub wybrać inną opcję z listy poniżej.");

            else
                model.addAttribute("conclusion", "Wybrana kamera bardzo dobrze łączy się z danym rejestratorem i switchem.");
            
            model.addAttribute("listRecorder", resultRecorder);
            model.addAttribute("listCamera", resultCamera);
            model.addAttribute("listSwitch", resultSwitch);

            model.addAttribute("option2", camera);
            model.addAttribute("option3", switchPOE);

            model.addAttribute("option1", recorder);
            
        }
        return "configuration_result";
        
    }
}
