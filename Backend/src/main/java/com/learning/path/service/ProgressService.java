package com.learning.path.service;

import com.learning.path.model.Progress;
import java.util.List;
import java.util.Optional;
import java.util.Map;

public interface ProgressService {
    Progress updateProgress(Long userId, Long topicId, int percentage);
    List<Progress> getUserProgress(Long userId);
    Optional<Progress> getProgressByUserAndTopic(Long userId, Long topicId);
    double getOverallProgress(Long userId, Long learningPathId);
    Map<String, Integer> getProgressByCategory(Long userId);
    double getOverallProgress(Long userId);
    Long getTotalTimeSpent(Long userId);
    List<String> getUserStrengths(Long userId);
}