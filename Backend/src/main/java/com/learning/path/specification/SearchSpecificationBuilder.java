package com.learning.path.specification;

import com.learning.path.model.LearningPath;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class SearchSpecificationBuilder {

    public Specification<LearningPath> buildSpecification(Map<String, String> searchCriteria) {
        List<Specification<LearningPath>> specifications = new ArrayList<>();

        searchCriteria.forEach((key, value) -> {
            if (value != null && !value.isEmpty()) {
                specifications.add(createSpecification(key, value));
            }
        });

        return specifications.stream()
                .reduce(Specification::and)
                .orElse(null);
    }

    private Specification<LearningPath> createSpecification(String key, String value) {
        return switch (key) {
            case "title" -> (root, query, cb) ->
                    cb.like(cb.lower(root.get(key)), "%" + value.toLowerCase() + "%");
            case "difficulty" -> (root, query, cb) ->
                    cb.equal(root.get(key), value);
            case "minRating" -> (root, query, cb) ->
                    cb.greaterThanOrEqualTo(root.get("averageRating"), Double.valueOf(value));
            case "maxDuration" -> (root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("estimatedHours"), Integer.valueOf(value));
            default -> (root, query, cb) -> null;
        };
    }
}