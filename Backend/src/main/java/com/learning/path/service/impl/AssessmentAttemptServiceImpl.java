package com.learning.path.service.impl;

import com.learning.path.model.AssessmentAttempt;
import com.learning.path.model.Assessment;
import com.learning.path.model.User;
import com.learning.path.dto.AssessmentAttemptDTO;
import com.learning.path.repository.AssessmentAttemptRepository;
import com.learning.path.service.AssessmentAttemptService;
import com.learning.path.service.AssessmentService;
import com.learning.path.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssessmentAttemptServiceImpl implements AssessmentAttemptService {

    @Autowired
    private AssessmentAttemptRepository attemptRepository;

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private UserService userService;

    @Override
    public AssessmentAttempt submitAttempt(AssessmentAttemptDTO attemptDTO) {
        User user = userService.getUserById(attemptDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Assessment assessment = assessmentService.getAssessmentById(attemptDTO.getAssessmentId())
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        AssessmentAttempt attempt = new AssessmentAttempt();
        attempt.setUser(user);
        attempt.setAssessment(assessment);
        attempt.setUserAnswer(attemptDTO.getUserAnswer());
        attempt.setScore(calculateScore(attemptDTO.getUserAnswer(), assessment.getCorrectAnswer()));
        attempt.setAttemptDate(LocalDateTime.now());

        return attemptRepository.save(attempt);
    }

    @Override
    public Optional<AssessmentAttempt> getAttemptById(Long id) {
        return attemptRepository.findById(id);
    }

    @Override
    public List<AssessmentAttempt> getUserAttempts(Long userId) {
        return attemptRepository.findByUserId(userId);
    }

    @Override
    public List<AssessmentAttempt> getAssessmentAttempts(Long assessmentId) {
        return attemptRepository.findByAssessmentId(assessmentId);
    }

    @Override
    public Integer calculateScore(String userAnswer, String correctAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer) ? 100 : 0;
    }
}