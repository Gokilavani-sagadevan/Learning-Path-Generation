package com.learning.path.repository;

import com.learning.path.model.AssessmentAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssessmentAttemptRepository extends JpaRepository<AssessmentAttempt, Long> {
    List<AssessmentAttempt> findByUserId(Long userId);
    List<AssessmentAttempt> findByAssessmentId(Long assessmentId);
    List<AssessmentAttempt> findByUserIdAndAssessmentId(Long userId, Long assessmentId);
}