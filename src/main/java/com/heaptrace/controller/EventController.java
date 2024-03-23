package com.heaptrace.controller;

import com.heaptrace.dto.Customer;
import com.heaptrace.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer/app")
@CrossOrigin("*")
public class EventController {
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message){
        try{
            for (int i = 0; i < 10000; i++){
                kafkaMessagePublisher.sendMessageToTopic(message + " : "+i);
            }
            return ResponseEntity.ok("Messages published successfully");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish/events")
    public ResponseEntity<?> publishEvents(@RequestBody Customer customer){
        kafkaMessagePublisher.sendEvents(customer);
        return ResponseEntity.ok("Customer event published successfully");
    }
}
