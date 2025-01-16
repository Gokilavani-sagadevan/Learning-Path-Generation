package com.learning.path.dto;

import lombok.Data;
import java.util.Map;
import java.util.List;

@Data
public class DashboardDTO {
    private UserStatisticsDTO userStats;
    private List<LearningPathDTO> inProgressCourses;  // note the correct spelling
    private List<CertificateDTO> recentCertificates;
    private Map<String, Integer> progressByCategory;
    private List<AssessmentDTO> upcomingAssessments;


    // Getters and setters
    public UserStatisticsDTO getUserStats() { return userStats; }
    public void setUserStats(UserStatisticsDTO userStats) { this.userStats = userStats; }

    public List<LearningPathDTO> getInProgressCourses() { return inProgressCourses; }

    public void setInProgressCourses(List<LearningPathDTO> inProgressCourses) { // correct method name
        this.inProgressCourses = inProgressCourses;
    }

    public List<CertificateDTO> getRecentCertificates() { return recentCertificates; }
    public void setRecentCertificates(List<CertificateDTO> recentCertificates) {
        this.recentCertificates = recentCertificates;
    }

    public Map<String, Integer> getProgressByCategory() { return progressByCategory; }
    public void setProgressByCategory(Map<String, Integer> progressByCategory) {
        this.progressByCategory = progressByCategory;
    }

    public List<AssessmentDTO> getUpcomingAssessments() { return upcomingAssessments; }
    public void setUpcomingAssessments(List<AssessmentDTO> upcomingAssessments) {
        this.upcomingAssessments = upcomingAssessments;
    }
}