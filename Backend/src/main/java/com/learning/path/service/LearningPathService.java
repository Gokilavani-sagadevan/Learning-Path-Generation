package com.learning.path.service;

import com.learning.path.dto.LearningPathDTO;
import com.learning.path.model.LearningPath;
import java.util.List;
import java.util.Optional;

public interface LearningPathService {
    LearningPath createLearningPath(LearningPath learningPath);
    Optional<LearningPath> getLearningPathById(Long id);
    List<LearningPath> getAllLearningPaths();
    List<LearningPath> getLearningPathsByUserId(Long userId);
    LearningPath updateLearningPath(Long id, LearningPath learningPath);
    void deleteLearningPath(Long id);
    List<LearningPath> searchLearningPaths(String keyword);
    List<LearningPath> filterByDifficulty(String difficulty);
    List<LearningPath> filterByDuration(Integer maxHours);
    List<LearningPathDTO> getInProgressPaths(Long userId);
}