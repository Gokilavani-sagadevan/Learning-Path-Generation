package com.learning.path.repository;

import com.learning.path.model.LearningPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LearningPathRepository extends JpaRepository<LearningPath, Long>, JpaSpecificationExecutor<LearningPath> {
    List<LearningPath> findByDifficulty(String difficulty);

    List<LearningPath> findByUserIdAndStatus(Long userId, String status);

    @Query("SELECT lp FROM LearningPath lp WHERE " +
            "LOWER(lp.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(lp.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<LearningPath> search(@Param("keyword") String keyword);

    List<LearningPath> findByEstimatedHoursLessThanEqual(Integer hours);

    List<LearningPath> findByCreatorId(Long userId);
}