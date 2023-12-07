package com.example.strona.model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Object> searchResults;

        if (query != null && !query.isEmpty()) {
            // Przykładowa logika wyszukiwania po nazwie lub ID
            //searchResults = productService.searchByNameOrId(query);
        } else {
            // Brak wartości w polu wyszukiwania
            searchResults = Collections.emptyList();
        }
       // model.addAttribute("searchResults", searchResults);

        return "searchResults";
    }
}
