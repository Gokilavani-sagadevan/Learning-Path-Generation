package com.learning.path.controller;

import com.learning.path.model.LearningPath;
import com.learning.path.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/search")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping
    public ResponseEntity<Page<LearningPath>> search(
            @RequestParam Map<String, String> searchParams,
            Pageable pageable) {
        return ResponseEntity.ok(searchService.search(searchParams, pageable));
    }

    @GetMapping("/advanced")
    public ResponseEntity<Page<LearningPath>> advancedSearch(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String difficulty,
            @RequestParam(required = false) Double minRating,
            Pageable pageable) {
        return ResponseEntity.ok(
                searchService.advancedSearch(keyword, difficulty, minRating, pageable)
        );
    }
}