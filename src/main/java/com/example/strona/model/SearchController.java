package com.example.strona.model;

import com.example.strona.model.Recorder.RecorderService;
import com.example.strona.model.camera.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    private final CameraService cameraService;
    private final RecorderService recorderService;

    @Autowired
    public SearchController(CameraService cameraService, RecorderService recorderService) {
        this.cameraService = cameraService;
        this.recorderService = recorderService;
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Object> searchResults;

        if (query != null && !query.isEmpty()) {
            // Przykładowa logika wyszukiwania po nazwie lub ID
            searchResults = Collections.singletonList(cameraService.getByNameOrId(query));
            if (searchResults.get(0).equals(Collections.emptyList())) {
                searchResults = Collections.singletonList(recorderService.getByNameOrId(query));
            }else {
                searchResults = Collections.emptyList();
                model.addAttribute("emptyResult", searchResults);
                return "searchResultForm";
            }
        } else {
            searchResults = Collections.emptyList();
            model.addAttribute("emptyResult", searchResults);
            return "searchResultForm";
        }
        model.addAttribute("searchResults", searchResults);

        return "searchResultForm";
    }
}
