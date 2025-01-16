package com.learning.path.controller;

import com.learning.path.dto.LearningPathDTO;
import com.learning.path.model.LearningPath;
import com.learning.path.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/learning-paths")
@CrossOrigin(origins = "http://localhost:3000")
public class LearningPathController {

    private final LearningPathService learningPathService;

    @Autowired
    public LearningPathController(LearningPathService learningPathService) {
        this.learningPathService = learningPathService;
    }

    @PostMapping
    public ResponseEntity<LearningPathDTO> createLearningPath(@RequestBody LearningPath learningPath) {
        LearningPath createdPath = learningPathService.createLearningPath(learningPath);
        return ResponseEntity.ok(convertToDTO(createdPath));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPathDTO> getLearningPath(@PathVariable Long id) {
        return learningPathService.getLearningPathById(id)
                .map(path -> ResponseEntity.ok(convertToDTO(path)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LearningPathDTO>> getAllLearningPaths() {
        List<LearningPathDTO> paths = learningPathService.getAllLearningPaths().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paths);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPathDTO>> getUserLearningPaths(@PathVariable Long userId) {
        List<LearningPathDTO> paths = learningPathService.getLearningPathsByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paths);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPathDTO> updateLearningPath(
            @PathVariable Long id,
            @RequestBody LearningPath learningPath) {
        LearningPath updatedPath = learningPathService.updateLearningPath(id, learningPath);
        return ResponseEntity.ok(convertToDTO(updatedPath));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLearningPath(@PathVariable Long id) {
        learningPathService.deleteLearningPath(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LearningPath>> searchLearningPaths(
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(learningPathService.searchLearningPaths(keyword));
    }

    @GetMapping("/filter/difficulty/{difficulty}")
    public ResponseEntity<List<LearningPath>> filterByDifficulty(
            @PathVariable String difficulty) {
        return ResponseEntity.ok(learningPathService.filterByDifficulty(difficulty));
    }

    @GetMapping("/filter/duration/{maxHours}")
    public ResponseEntity<List<LearningPath>> filterByDuration(
            @PathVariable Integer maxHours) {
        return ResponseEntity.ok(learningPathService.filterByDuration(maxHours));
    }

    private LearningPathDTO convertToDTO(LearningPath learningPath) {
        LearningPathDTO dto = new LearningPathDTO();
        dto.setId(learningPath.getId());
        dto.setTitle(learningPath.getTitle());
        dto.setDescription(learningPath.getDescription());
        dto.setDifficulty(learningPath.getDifficulty());
        dto.setEstimatedHours(learningPath.getEstimatedHours());
        dto.setCreatorId(learningPath.getCreator().getId());
        return dto;
    }
}