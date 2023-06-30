package com.kafkaMock.KafkaProject;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class ConsumerService {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(ConsumerRecord<String, String> record) {
        String jsonDocument = record.value();
        System.out.println("Received JSON document: " + jsonDocument);
    }
}
