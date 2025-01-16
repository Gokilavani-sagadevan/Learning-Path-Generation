package com.learning.path.repository;

import com.learning.path.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    List<Assessment> findByTopicId(Long topicId);

    @Query("SELECT a FROM Assessment a WHERE a.dueDate > CURRENT_TIMESTAMP " +
            "AND a.topic.learningPath.user.id = :userId ORDER BY a.dueDate")
    List<Assessment> findUpcomingByUserId(@Param("userId") Long userId);

    @Query("SELECT a.score FROM AssessmentAttempt a WHERE a.user.id = :userId")
    List<Double> findUserScores(@Param("userId") Long userId);
}