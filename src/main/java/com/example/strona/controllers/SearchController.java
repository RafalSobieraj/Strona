package com.example.strona.controllers;

import com.example.strona.model.Recorder.RecorderService;
import com.example.strona.model.camera.CameraService;
import com.example.strona.model.switchPOE.SwitchPOEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    private final CameraService cameraService;
    private final SwitchPOEService switchPOEService;
    private final RecorderService recorderService;

    @Autowired
    public SearchController(CameraService cameraService, SwitchPOEService switchPOEService,
                            RecorderService recorderService) {
        this.cameraService = cameraService;
        this.switchPOEService = switchPOEService;
        this.recorderService = recorderService;
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Object> searchResults = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            searchResults.addAll(switchPOEService.searchByIdOrName(query));
            searchResults.addAll(cameraService.searchByIdOrName(query));
            searchResults.addAll(recorderService.searchByIdOrName(query));
        } else {
            searchResults = Collections.emptyList();
        }
        model.addAttribute("searchResults", searchResults);

        return "searchResults";
    }
}
