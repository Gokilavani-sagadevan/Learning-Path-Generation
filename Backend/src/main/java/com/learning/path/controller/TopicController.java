package com.learning.path.controller;

import com.learning.path.dto.TopicDTO;
import com.learning.path.model.Topic;
import com.learning.path.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin(origins = "http://localhost:3000")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicDTO> createTopic(@RequestBody Topic topic) {
        Topic createdTopic = topicService.createTopic(topic);
        return ResponseEntity.ok(convertToDTO(createdTopic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicDTO> getTopic(@PathVariable Long id) {
        return topicService.getTopicById(id)
                .map(topic -> ResponseEntity.ok(convertToDTO(topic)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/learning-path/{learningPathId}")
    public ResponseEntity<List<TopicDTO>> getTopicsByLearningPath(@PathVariable Long learningPathId) {
        List<TopicDTO> topics = topicService.getTopicsByLearningPathId(learningPathId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(topics);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicDTO> updateTopic(@PathVariable Long id, @RequestBody Topic topic) {
        Topic updatedTopic = topicService.updateTopic(id, topic);
        return ResponseEntity.ok(convertToDTO(updatedTopic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok().build();
    }

    private TopicDTO convertToDTO(Topic topic) {
        TopicDTO dto = new TopicDTO();
        dto.setId(topic.getId());
        dto.setName(topic.getName());
        dto.setDescription(topic.getDescription());
        dto.setOrderIndex(topic.getOrderIndex());
        dto.setLearningPathId(topic.getLearningPath().getId());
        return dto;
    }
}