package com.example.producer;

import com.example.model.Message;
import com.example.wrapper.OwnerWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;

@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void produce(Message message) {
        System.out.println("Producing the message: " + message);
        kafkaTemplate.send("messages", message);
    }

    @KafkaListener(topics = "messagesFinal", groupId = "message_group_id")
    public void produceConsumer(Message message) {
        System.out.println("Consumer th message: " + message);
    }

}
