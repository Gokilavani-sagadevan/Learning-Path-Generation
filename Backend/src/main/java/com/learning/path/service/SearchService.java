package com.learning.path.service;

import com.learning.path.model.LearningPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Map;

public interface SearchService {
    Page<LearningPath> search(Map<String, String> searchCriteria, Pageable pageable);
    Page<LearningPath> advancedSearch(String keyword, String difficulty, Double minRating, Pageable pageable);
}