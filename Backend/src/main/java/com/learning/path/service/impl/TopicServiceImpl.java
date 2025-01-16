package com.learning.path.service.impl;

import com.learning.path.model.Topic;
import com.learning.path.repository.TopicRepository;
import com.learning.path.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    @Override
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> getTopicsByLearningPathId(Long learningPathId) {
        return topicRepository.findByLearningPathIdOrderByOrderIndex(learningPathId);
    }

    @Override
    public Topic updateTopic(Long id, Topic topicDetails) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        topic.setName(topicDetails.getName());
        topic.setDescription(topicDetails.getDescription());
        topic.setOrderIndex(topicDetails.getOrderIndex());

        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}