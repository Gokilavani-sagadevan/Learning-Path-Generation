package com.learning.path.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {
    private Long id;
    private String name;
    private String description;
    private Integer orderIndex;
    private Long learningPathId;
    private List<ResourceDTO> resources;

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public Long getLearningPathId() {
        return learningPathId;
    }

    public List<ResourceDTO> getResources() {
        return resources;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public void setLearningPathId(Long learningPathId) {
        this.learningPathId = learningPathId;
    }

    public void setResources(List<ResourceDTO> resources) {
        this.resources = resources;
    }
}