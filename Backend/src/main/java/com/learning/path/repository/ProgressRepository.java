package com.learning.path.repository;

import com.learning.path.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ProgressRepository extends JpaRepository<Progress, Long> {
    @Query("SELECT new map(p.category as category, COUNT(p) as count) " +
            "FROM Progress p WHERE p.user.id = :userId GROUP BY p.category")
    Map<String, Integer> findProgressByCategory(@Param("userId") Long userId);

    @Query("SELECT AVG(p.completionPercentage) FROM Progress p WHERE p.user.id = :userId")
    double calculateOverallProgress(@Param("userId") Long userId);

    @Query("SELECT SUM(p.timeSpent) FROM Progress p WHERE p.user.id = :userId")
    Long calculateTotalTimeSpent(@Param("userId") Long userId);

    @Query(nativeQuery = true, value = "SELECT category FROM progress " +
            "WHERE user_id = :userId GROUP BY category HAVING AVG(score) >= 80")
    List<String> findUserStrengths(@Param("userId") Long userId);
    Optional<Progress> findByUserIdAndTopicId(Long userId, Long topicId);

    List<Progress> findByUserId(Long userId);

    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId AND p.topic.learningPath.id = :learningPathId")
    List<Progress> findByUserIdAndLearningPathId(@Param("userId") Long userId, @Param("learningPathId") Long learningPathId);
}