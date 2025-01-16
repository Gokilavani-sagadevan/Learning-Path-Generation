package com.learning.path.service.impl;

import com.learning.path.model.Rating;
import com.learning.path.model.User;
import com.learning.path.model.LearningPath;
import com.learning.path.dto.RatingDTO;
import com.learning.path.repository.RatingRepository;
import com.learning.path.service.RatingService;
import com.learning.path.service.UserService;
import com.learning.path.service.LearningPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LearningPathService learningPathService;

    @Override
    public Rating createRating(RatingDTO ratingDTO) {
        User user = userService.getUserById(ratingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        LearningPath learningPath = learningPathService.getLearningPathById(ratingDTO.getLearningPathId())
                .orElseThrow(() -> new RuntimeException("Learning Path not found"));

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setLearningPath(learningPath);
        rating.setRating(ratingDTO.getRating());
        rating.setReview(ratingDTO.getReview());

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateRating(Long id, RatingDTO ratingDTO) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found"));

        rating.setRating(ratingDTO.getRating());
        rating.setReview(ratingDTO.getReview());

        return ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public List<Rating> getLearningPathRatings(Long learningPathId) {
        return ratingRepository.findByLearningPathId(learningPathId);
    }

    @Override
    public double getAverageRating(Long learningPathId) {
        List<Rating> ratings = getLearningPathRatings(learningPathId);
        if (ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);
    }
}