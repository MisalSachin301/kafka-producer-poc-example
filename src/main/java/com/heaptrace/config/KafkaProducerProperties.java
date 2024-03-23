package com.heaptrace.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaProducerProperties {
    private String name;
    private Integer numPartitions;
    private Short replicationFactor;
}
