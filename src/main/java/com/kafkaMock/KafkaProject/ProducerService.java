package com.kafkaMock.KafkaProject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class ProducerService implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProducerService(KafkaTemplate<String, String> kafkaTemplate,
                           @Value("${spring.kafka.topic.name}") String topicName,
                           RestTemplate restTemplate,
                           ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) {
        String apiUrl = "https://mocki.io/v1/d4867d8b-b5d5-4a48-a4ab-79131b5809b8";
        String jsonResponse = restTemplate.getForObject(apiUrl, String.class);

        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            if (jsonNode.isArray()) {
                for (JsonNode document : jsonNode) {
                    String jsonString = document.toString();
                    kafkaTemplate.send(new ProducerRecord<>(topicName, jsonString));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}