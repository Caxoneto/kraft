package com.kraft.krafting.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName = "topic1";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public KafkaProducer<String, String> createKafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-mxqvx.europe-southwest1.gcp.confluent.cloud:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("spring.kafka.properties.sasl.mechanism", "PLAIN");
        properties.put("spring.kafka.properties.sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=57EV3XQL473LDKEE password=9M0DglOX9BWQJfKBVZUTaJCi4D8YVQA9meHnaovI8/RC8rRtaktBsTw46mFTaMPV;");
        properties.put("spring.kafka.properties.security.protocol", "SASL_SSL");
        //properties.put("sasl.mechanism", kafkaProperties.getSaslMechanism());
        
        // Configura otros valores de propiedades aquí
        
        return new KafkaProducer<>(properties);
    }
    
    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send(topicName, mensaje);
    }
}

