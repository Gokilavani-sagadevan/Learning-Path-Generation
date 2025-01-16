package com.learning.path.service.impl;

import com.learning.path.model.LearningPath;
import com.learning.path.repository.LearningPathRepository;
import com.learning.path.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.learning.path.specification.SearchSpecificationBuilder;

import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private LearningPathRepository learningPathRepository;

    @Autowired
    private SearchSpecificationBuilder specificationBuilder;

    @Override
    public Page<LearningPath> search(Map<String, String> searchCriteria, Pageable pageable) {
        Specification<LearningPath> spec = specificationBuilder.buildSpecification(searchCriteria);
        return learningPathRepository.findAll(spec, pageable);
    }

    @Override
    public Page<LearningPath> advancedSearch(String keyword, String difficulty, Double minRating, Pageable pageable) {
        Specification<LearningPath> spec = Specification
                .where(containsKeyword(keyword))
                .and(hasDifficulty(difficulty))
                .and(hasMinimumRating(minRating));

        return learningPathRepository.findAll(spec, pageable);
    }

    private Specification<LearningPath> containsKeyword(String keyword) {
        return (root, query, cb) -> {
            if (keyword == null || keyword.isEmpty()) {
                return null;
            }
            return cb.or(
                    cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%"),
                    cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%")
            );
        };
    }

    private Specification<LearningPath> hasDifficulty(String difficulty) {
        return (root, query, cb) -> {
            if (difficulty == null || difficulty.isEmpty()) {
                return null;
            }
            return cb.equal(root.get("difficulty"), difficulty);
        };
    }

    private Specification<LearningPath> hasMinimumRating(Double minRating) {
        return (root, query, cb) -> {
            if (minRating == null) {
                return null;
            }
            return cb.greaterThanOrEqualTo(root.get("averageRating"), minRating);
        };
    }
}