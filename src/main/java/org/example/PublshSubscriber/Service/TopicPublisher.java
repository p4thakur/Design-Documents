package org.example.PublshSubscriber.Service;

import org.example.PublshSubscriber.model.Topic;

public class TopicPublisher {
    private final Topic topic;
    private final IPublisher publisher;

    public TopicPublisher(Topic topic, IPublisher publisher) {
        this.topic = topic;
        this.publisher = publisher;
    }
    public Topic getTopic() {
        return topic;
    }

    public IPublisher getPublisher() {
        return publisher;
    }
}