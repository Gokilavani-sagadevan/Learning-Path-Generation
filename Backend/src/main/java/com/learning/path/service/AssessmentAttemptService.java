package com.learning.path.service;

import com.learning.path.model.AssessmentAttempt;
import com.learning.path.dto.AssessmentAttemptDTO;
import java.util.List;
import java.util.Optional;

public interface AssessmentAttemptService {
    AssessmentAttempt submitAttempt(AssessmentAttemptDTO attemptDTO);
    Optional<AssessmentAttempt> getAttemptById(Long id);
    List<AssessmentAttempt> getUserAttempts(Long userId);
    List<AssessmentAttempt> getAssessmentAttempts(Long assessmentId);
    Integer calculateScore(String userAnswer, String correctAnswer);
}