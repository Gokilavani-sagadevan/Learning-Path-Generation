package com.learning.path.repository;

import com.learning.path.model.UserAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAnalyticsRepository extends JpaRepository<UserAnalytics, Long> {

    Optional<UserAnalytics> findByUserId(Long userId);

    @Query("SELECT ua FROM UserAnalytics ua WHERE ua.totalCoursesCompleted > :minCourses")
    List<UserAnalytics> findActiveUsers(@Param("minCourses") Integer minCourses);

    @Query("SELECT AVG(ua.averageScore) FROM UserAnalytics ua")
    Double getAveragePlatformScore();

    @Query("SELECT ua FROM UserAnalytics ua ORDER BY ua.totalCoursesCompleted DESC LIMIT 10")
    List<UserAnalytics> findTopPerformers();

    @Query("SELECT COUNT(ua) FROM UserAnalytics ua WHERE ua.lastActivityDate >= CURRENT_DATE - 30")
    Long getActiveUsersLastMonth();
}