package org.example.PublshSubscriber.Service;


import org.example.PublshSubscriber.Controllers.KafkaController;
import org.example.PublshSubscriber.model.Message;

public class SimplePublisher implements IPublisher {
    private final String id;
    private final KafkaController kafkaController;

    public SimplePublisher(String id, KafkaController kafkaController) {
        this.id = id;
        this.kafkaController = kafkaController;
    }

    @Override
    public String getId() {
        return id;
    }
    @Override
    public void publish(String topicId, Message message) throws IllegalArgumentException {
        kafkaController.publish(this, topicId, message);
        System.out.println("Publisher " + id + " published: " + message.getMessage() + " to topic " + topicId);
    }
}