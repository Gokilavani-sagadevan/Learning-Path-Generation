package com.learning.path.service;

import com.learning.path.model.Assessment;
import com.learning.path.dto.AssessmentDTO;
import java.util.List;
import java.util.Optional;

public interface AssessmentService {
    Assessment createAssessment(AssessmentDTO assessmentDTO);
    Optional<Assessment> getAssessmentById(Long id);
    List<Assessment> getAssessmentsByTopicId(Long topicId);
    Assessment updateAssessment(Long id, AssessmentDTO assessmentDTO);
    void deleteAssessment(Long id);
    List<AssessmentDTO> getUpcomingAssessments(Long userId);
    List<Double> getUserScores(Long userId);
}