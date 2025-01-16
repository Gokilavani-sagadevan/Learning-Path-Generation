package com.learning.path.controller;

import com.learning.path.model.Progress;
import com.learning.path.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = "http://localhost:3000")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @PostMapping("/update/{userId}/{topicId}")
    public ResponseEntity<Progress> updateProgress(
            @PathVariable Long userId,
            @PathVariable Long topicId,
            @RequestBody Map<String, Integer> progressUpdate) {
        Progress updated = progressService.updateProgress(
                userId,
                topicId,
                progressUpdate.get("percentage")
        );
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Progress>> getUserProgress(@PathVariable Long userId) {
        return ResponseEntity.ok(progressService.getUserProgress(userId));
    }

    @GetMapping("/{userId}/topic/{topicId}")
    public ResponseEntity<Progress> getSpecificProgress(
            @PathVariable Long userId,
            @PathVariable Long topicId) {
        return progressService.getProgressByUserAndTopic(userId, topicId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}/learning-path/{learningPathId}")
    public ResponseEntity<Double> getOverallProgress(
            @PathVariable Long userId,
            @PathVariable Long learningPathId) {
        double progress = progressService.getOverallProgress(userId, learningPathId);
        return ResponseEntity.ok(progress);
    }
}