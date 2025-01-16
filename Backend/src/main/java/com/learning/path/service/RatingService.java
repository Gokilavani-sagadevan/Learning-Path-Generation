package com.learning.path.service;

import com.learning.path.model.Rating;
import com.learning.path.dto.RatingDTO;
import java.util.List;

public interface RatingService {
    Rating createRating(RatingDTO ratingDTO);
    Rating updateRating(Long id, RatingDTO ratingDTO);
    void deleteRating(Long id);
    List<Rating> getLearningPathRatings(Long learningPathId);
    double getAverageRating(Long learningPathId);
}