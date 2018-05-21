package com.roshan.springkafkaconsumer.listener;

import com.roshan.springkafkaconsumer.model.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author ali akbar azizkhani
 */
@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Example")
    public void consume(User user) {
        System.out.println("Consume messsage :" + user.toString());
    }
}
