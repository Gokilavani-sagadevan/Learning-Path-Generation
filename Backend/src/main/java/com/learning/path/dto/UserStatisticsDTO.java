package com.learning.path.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserStatisticsDTO {
    private Long userId;
    private Integer totalCoursesStarted;
    private Integer totalCoursesCompleted;
    private Integer totalAssessmentsTaken;
    private Double averageScore;
    private Integer totalCertificatesEarned;
    private Integer currentStreak;
    private Long totalTimeSpent;  // in minutes
    private Double completionRate;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalCoursesStarted() {
        return totalCoursesStarted;
    }

    public void setTotalCoursesStarted(Integer totalCoursesStarted) {
        this.totalCoursesStarted = totalCoursesStarted;
    }

    public Integer getTotalCoursesCompleted() {
        return totalCoursesCompleted;
    }

    public void setTotalCoursesCompleted(Integer totalCoursesCompleted) {
        this.totalCoursesCompleted = totalCoursesCompleted;
    }

    public Integer getTotalAssessmentsTaken() {
        return totalAssessmentsTaken;
    }

    public void setTotalAssessmentsTaken(Integer totalAssessmentsTaken) {
        this.totalAssessmentsTaken = totalAssessmentsTaken;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getTotalCertificatesEarned() {
        return totalCertificatesEarned;
    }

    public void setTotalCertificatesEarned(Integer totalCertificatesEarned) {
        this.totalCertificatesEarned = totalCertificatesEarned;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Long totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public Double getCompletionRate() {
        return completionRate;
    }

    public void setCompletionRate(Double completionRate) {
        this.completionRate = completionRate;
    }
}