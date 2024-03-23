package com.heaptrace.service;

import com.heaptrace.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message){
        CompletableFuture<SendResult<String, Object>> completableFuture = template.send("heaptrace", message);

        completableFuture.whenComplete((result, exception) -> {
            if(exception == null){
                log.info("Sent message=[{}] with offset=[{}] ",message, result.getRecordMetadata().offset());
                log.info("Partition: {}", result.getRecordMetadata().partition());
            } else {
                log.error("Unable to send message=[{}] due to: {}", message, exception.getMessage());
            }
        });
    }

    public void sendEvents(Customer customer){
        try{
            String key = UUID.randomUUID().toString();
            log.info("UUID: {}",key);
            CompletableFuture<SendResult<String, Object>> completableFuture = template.send("heaptrace",customer);
            completableFuture.whenComplete((result, exception) -> {
                if(exception == null){
                    log.info("Sent event=[{}] with offset=[{}] ", customer.toString(), result.getRecordMetadata().offset());
                    log.info("Partition: {}", result.getRecordMetadata().partition());
                } else {
                    log.error("Unable to event=[{}] due to: {}", customer.toString(), exception.getMessage());
                }
            });

        } catch (Exception exception){
            log.error("Exception occurred: {}", exception.getMessage());
        }
    }
}
