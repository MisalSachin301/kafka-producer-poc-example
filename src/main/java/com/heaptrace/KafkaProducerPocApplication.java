package com.heaptrace;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerPocApplication.class, args);
	}

}
