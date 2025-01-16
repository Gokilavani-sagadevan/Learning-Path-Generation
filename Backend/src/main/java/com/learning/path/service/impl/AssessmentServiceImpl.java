package com.learning.path.service.impl;

import com.learning.path.model.Assessment;
import com.learning.path.model.Topic;
import com.learning.path.dto.AssessmentDTO;
import com.learning.path.repository.AssessmentRepository;
import com.learning.path.service.AssessmentService;
import com.learning.path.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private TopicService topicService;

    @Override
    public Assessment createAssessment(AssessmentDTO assessmentDTO) {
        Topic topic = topicService.getTopicById(assessmentDTO.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        Assessment assessment = new Assessment();
        assessment.setTopic(topic);
        assessment.setQuestion(assessmentDTO.getQuestion());
        assessment.setCorrectAnswer(assessmentDTO.getCorrectAnswer());
        assessment.setMaxScore(assessmentDTO.getMaxScore());
        assessment.setOptions(assessmentDTO.getOptions());

        return assessmentRepository.save(assessment);
    }

    private AssessmentDTO convertToDTO(Assessment assessment) {
        AssessmentDTO dto = new AssessmentDTO();
        dto.setId(assessment.getId());
        dto.setTopicId(assessment.getTopic().getId());
        dto.setQuestion(assessment.getQuestion());
        dto.setCorrectAnswer(assessment.getCorrectAnswer());
        dto.setMaxScore(assessment.getMaxScore());
        dto.setOptions(assessment.getOptions());
        return dto;
    }

    @Override
    public Optional<Assessment> getAssessmentById(Long id) {
        return assessmentRepository.findById(id);
    }

    @Override
    public List<Assessment> getAssessmentsByTopicId(Long topicId) {
        return assessmentRepository.findByTopicId(topicId);
    }

    @Override
    public Assessment updateAssessment(Long id, AssessmentDTO assessmentDTO) {
        Assessment assessment = assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        Topic topic = topicService.getTopicById(assessmentDTO.getTopicId())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        assessment.setTopic(topic);
        assessment.setQuestion(assessmentDTO.getQuestion());
        assessment.setCorrectAnswer(assessmentDTO.getCorrectAnswer());
        assessment.setMaxScore(assessmentDTO.getMaxScore());
        assessment.setOptions(assessmentDTO.getOptions());

        return assessmentRepository.save(assessment);
    }

    @Override
    public void deleteAssessment(Long id) {
        assessmentRepository.deleteById(id);
    }

    @Override
    public List<AssessmentDTO> getUpcomingAssessments(Long userId) {
        List<Assessment> assessments = assessmentRepository
                .findUpcomingByUserId(userId);
        return assessments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Double> getUserScores(Long userId) {
        return assessmentRepository.findUserScores(userId);
    }
}