package com.learning.path.repository;

import com.learning.path.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByLearningPathIdOrderByOrderIndex(Long learningPathId);
}