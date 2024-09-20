package com.spring.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(value = "/order")
    public String postOrder(@RequestBody HashMap<String, String> requestBody){
        String orderId = requestBody.get("orderId");
        String json = "orderId : " + orderId;
        kafkaTemplate.send("payment", json);
        return "Your order is getting confirmed. You will be notified when payment is processed!!";
    }

    @PostMapping(value = "/payment")
    public String postPayment(@RequestBody HashMap<String, String> requestBody){
        String orderId = requestBody.get("orderId");
        String json = "orderId : " + orderId;
        kafkaTemplate.send("notification", json);
        return "You order with orderId : " +  orderId + " has been placed";
    }
}
