package com.learning.path.service;

import com.learning.path.model.LearningPath;
import java.util.List;

public interface RecommendationService {
    List<LearningPath> getPersonalizedRecommendations(Long userId);
    List<LearningPath> getSimilarLearningPaths(Long learningPathId);
    List<LearningPath> getPopularLearningPaths();
}