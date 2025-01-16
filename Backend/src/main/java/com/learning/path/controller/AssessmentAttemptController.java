package com.learning.path.controller;

import com.learning.path.model.AssessmentAttempt;
import com.learning.path.dto.AssessmentAttemptDTO;
import com.learning.path.service.AssessmentAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assessment-attempts")
@CrossOrigin(origins = "http://localhost:3000")
public class AssessmentAttemptController {

    @Autowired
    private AssessmentAttemptService attemptService;

    @PostMapping
    public ResponseEntity<AssessmentAttempt> submitAttempt(@Valid @RequestBody AssessmentAttemptDTO attemptDTO) {
        return ResponseEntity.ok(attemptService.submitAttempt(attemptDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssessmentAttempt> getAttempt(@PathVariable Long id) {
        return attemptService.getAttemptById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AssessmentAttempt>> getUserAttempts(@PathVariable Long userId) {
        return ResponseEntity.ok(attemptService.getUserAttempts(userId));
    }

    @GetMapping("/assessment/{assessmentId}")
    public ResponseEntity<List<AssessmentAttempt>> getAssessmentAttempts(@PathVariable Long assessmentId) {
        return ResponseEntity.ok(attemptService.getAssessmentAttempts(assessmentId));
    }
}