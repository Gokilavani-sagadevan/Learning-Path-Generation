package com.learning.path.controller;

import com.learning.path.model.Rating;
import com.learning.path.dto.RatingDTO;
import com.learning.path.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin(origins = "http://localhost:3000")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRating(@Valid @RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.createRating(ratingDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rating> updateRating(
            @PathVariable Long id,
            @Valid @RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/learning-path/{learningPathId}")
    public ResponseEntity<List<Rating>> getLearningPathRatings(
            @PathVariable Long learningPathId) {
        return ResponseEntity.ok(ratingService.getLearningPathRatings(learningPathId));
    }

    @GetMapping("/average/{learningPathId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long learningPathId) {
        return ResponseEntity.ok(ratingService.getAverageRating(learningPathId));
    }
}