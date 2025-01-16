package com.learning.path.service.impl;

import com.learning.path.dto.LearningPathDTO;
import com.learning.path.model.LearningPath;
import com.learning.path.repository.LearningPathRepository;
import com.learning.path.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LearningPathServiceImpl implements LearningPathService {

    @Autowired
    private final LearningPathRepository learningPathRepository;

    @Autowired
    public LearningPathServiceImpl(LearningPathRepository learningPathRepository) {
        this.learningPathRepository = learningPathRepository;
    }

    @Override
    public LearningPath createLearningPath(LearningPath learningPath) {
        return learningPathRepository.save(learningPath);
    }

    @Override
    public Optional<LearningPath> getLearningPathById(Long id) {
        return learningPathRepository.findById(id);
    }

    @Override
    public List<LearningPath> getAllLearningPaths() {
        return learningPathRepository.findAll();
    }

    @Override
    public LearningPath updateLearningPath(Long id, LearningPath learningPathDetails) {
        LearningPath learningPath = learningPathRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning Path not found"));

        learningPath.setTitle(learningPathDetails.getTitle());
        learningPath.setDescription(learningPathDetails.getDescription());
        learningPath.setDifficulty(learningPathDetails.getDifficulty());
        learningPath.setEstimatedHours(learningPathDetails.getEstimatedHours());

        return learningPathRepository.save(learningPath);
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

    @Override
    public void deleteLearningPath(Long id) {
        learningPathRepository.deleteById(id);
    }

    // New search and filter methods
    @Override
    public List<LearningPath> searchLearningPaths(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllLearningPaths();
        }
        return learningPathRepository.search(keyword);
    }

    @Override
    public List<LearningPath> filterByDifficulty(String difficulty) {
        if (difficulty == null || difficulty.trim().isEmpty()) {
            return getAllLearningPaths();
        }
        return learningPathRepository.findByDifficulty(difficulty);
    }

    @Override
    public List<LearningPath> filterByDuration(Integer maxHours) {
        if (maxHours == null || maxHours <= 0) {
            return getAllLearningPaths();
        }
        return learningPathRepository.findByEstimatedHoursLessThanEqual(maxHours);
    }

    @Override
    public List<LearningPath> getLearningPathsByUserId(Long userId) {
        if (userId == null) {
            throw new RuntimeException("User ID cannot be null");
        }
        return learningPathRepository.findByCreatorId(userId);
    }

    @Override
    public List<LearningPathDTO> getInProgressPaths(Long userId) {
        List<LearningPath> inProgressPaths = learningPathRepository
                .findByUserIdAndStatus(userId, "IN_PROGRESS");
        return inProgressPaths.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}