package com.learning.path.service;

import com.learning.path.model.UserAnalytics;
import java.util.Map;

public interface AnalyticsService {
    UserAnalytics getUserAnalytics(Long userId);
    Map<String, Object> getLearningPathAnalytics(Long learningPathId);
    Map<String, Object> getPlatformAnalytics();
    void updateUserAnalytics(Long userId);
}