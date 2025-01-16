package com.learning.path.controller;

import com.learning.path.model.Assessment;
import com.learning.path.dto.AssessmentDTO;
import com.learning.path.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@CrossOrigin(origins = "http://localhost:3000")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    @PostMapping
    public ResponseEntity<Assessment> createAssessment(@Valid @RequestBody AssessmentDTO assessmentDTO) {
        return ResponseEntity.ok(assessmentService.createAssessment(assessmentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Assessment> getAssessment(@PathVariable Long id) {
        return assessmentService.getAssessmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Assessment>> getAssessmentsByTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(assessmentService.getAssessmentsByTopicId(topicId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Assessment> updateAssessment(
            @PathVariable Long id,
            @Valid @RequestBody AssessmentDTO assessmentDTO) {
        return ResponseEntity.ok(assessmentService.updateAssessment(id, assessmentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return ResponseEntity.ok().build();
    }
}