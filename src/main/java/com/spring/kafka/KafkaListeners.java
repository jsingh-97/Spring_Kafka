package com.spring.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "payment", groupId = "default")
    void listener(String data){
        System.out.println("Listener received : " + data);
    }
}
