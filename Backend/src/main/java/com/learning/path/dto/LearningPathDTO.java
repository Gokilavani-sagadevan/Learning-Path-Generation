package com.learning.path.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearningPathDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Difficulty level is required")
    private String difficulty;

    @Min(value = 1, message = "Estimated hours must be at least 1")
    private Integer estimatedHours;

    private Long creatorId;
    private List<TopicDTO> topics;

    // Add getters and setters explicitly
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setEstimatedHours(Integer estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setTopics(List<TopicDTO> topics) {
        this.topics = topics;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public Integer getEstimatedHours() {
        return estimatedHours;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public List<TopicDTO> getTopics() {
        return topics;
    }
}