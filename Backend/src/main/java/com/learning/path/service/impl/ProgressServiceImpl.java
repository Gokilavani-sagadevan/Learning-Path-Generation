package com.learning.path.service.impl;

import com.learning.path.model.Progress;
import com.learning.path.model.Topic;
import com.learning.path.repository.ProgressRepository;
import com.learning.path.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Override
    public Progress updateProgress(Long userId, Long topicId, int percentage) {
        Progress progress = progressRepository.findByUserIdAndTopicId(userId, topicId)
                .orElse(new Progress());

        progress.setCompletionPercentage(percentage);
        progress.setLastAccessed(LocalDateTime.now());
        progress.setCompleted(percentage == 100);

        return progressRepository.save(progress);
    }

    @Override
    public List<Progress> getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId);
    }

    @Override
    public Optional<Progress> getProgressByUserAndTopic(Long userId, Long topicId) {
        return progressRepository.findByUserIdAndTopicId(userId, topicId);
    }

    @Override
    public double getOverallProgress(Long userId, Long learningPathId) {
        List<Progress> progressList = getUserProgress(userId);
        if (progressList.isEmpty()) return 0.0;

        return progressList.stream()
                .mapToInt(Progress::getCompletionPercentage)
                .average()
                .orElse(0.0);
    }

    @Override
    public Map<String, Integer> getProgressByCategory(Long userId) {
        return progressRepository.findProgressByCategory(userId);
    }

    @Override
    public double getOverallProgress(Long userId) {
        return progressRepository.calculateOverallProgress(userId);
    }

    @Override
    public Long getTotalTimeSpent(Long userId) {
        return progressRepository.calculateTotalTimeSpent(userId);
    }

    @Override
    public List<String> getUserStrengths(Long userId) {
        return progressRepository.findUserStrengths(userId);
    }
}