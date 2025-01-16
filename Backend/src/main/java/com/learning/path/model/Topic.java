package com.learning.path.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer orderIndex;

    @ManyToOne
    @JoinColumn(name = "learning_path_id")
    private LearningPath learningPath;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Resource> resources = new ArrayList<>();

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

    public LearningPath getLearningPath() {
        return learningPath;
    }

    public List<Resource> getResources() {
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

    public void setLearningPath(LearningPath learningPath) {
        this.learningPath = learningPath;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}