package com.kraft.krafting.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-mxqvx.europe-southwest1.gcp.confluent.cloud:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kraft-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("spring.kafka.properties.sasl.mechanism", "PLAIN");
        props.put("spring.kafka.properties.sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=57EV3XQL473LDKEE password=9M0DglOX9BWQJfKBVZUTaJCi4D8YVQA9meHnaovI8/RC8rRtaktBsTw46mFTaMPV;");
        props.put("spring.kafka.properties.security.protocol", "SASL_SSL");
        // Configura otros campos aquí

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        // Configura otros aspectos del listener aquí si es necesario
        return factory;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-mxqvx.europe-southwest1.gcp.confluent.cloud:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put("spring.kafka.properties.sasl.mechanism", "PLAIN");
        props.put("spring.kafka.properties.sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=57EV3XQL473LDKEE password=9M0DglOX9BWQJfKBVZUTaJCi4D8YVQA9meHnaovI8/RC8rRtaktBsTw46mFTaMPV;");
        props.put("spring.kafka.properties.security.protocol", "SASL_SSL");
        // Configura otros campos aquí

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Puedes incorporar otros métodos y configuraciones relacionadas con KafkaProperties aquí si es necesario
}

