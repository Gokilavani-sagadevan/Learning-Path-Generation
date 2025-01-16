package com.learning.path.service.impl;

import com.learning.path.dto.DashboardDTO;
import com.learning.path.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.learning.path.dto.UserStatisticsDTO;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserService userService;

    @Autowired
    private LearningPathService learningPathService;

    @Autowired
    private ProgressService progressService;

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private AssessmentService assessmentService;

    @Override
    public DashboardDTO getUserDashboard(Long userId) {
        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setUserStats(getUserStatistics(userId));
        dashboard.setInProgressCourses(learningPathService.getInProgressPaths(userId));  // Fixed spelling
        dashboard.setRecentCertificates(certificateService.getUserCertificates(userId));
        dashboard.setProgressByCategory(progressService.getProgressByCategory(userId));
        dashboard.setUpcomingAssessments(assessmentService.getUpcomingAssessments(userId));
        return dashboard;
    }

    @Override
    public Map<String, Object> getUserAnalytics(Long userId) {
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("completionRate", progressService.getOverallProgress(userId));
        analytics.put("assessmentScores", assessmentService.getUserScores(userId));
        analytics.put("timeSpent", progressService.getTotalTimeSpent(userId));
        analytics.put("strengthAreas", progressService.getUserStrengths(userId));
        return analytics;
    }

    private UserStatisticsDTO getUserStatistics(Long userId) {
        // Implementation for getting user statistics
        return null;
    }
}