package com.learning.path.repository;

import com.learning.path.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByLearningPathId(Long learningPathId);
    List<Rating> findByUserId(Long userId);
    Optional<Rating> findByUserIdAndLearningPathId(Long userId, Long learningPathId);
}