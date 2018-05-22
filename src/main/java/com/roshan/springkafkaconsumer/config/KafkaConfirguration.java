package com.roshan.springkafkaconsumer.config;

import com.roshan.springkafkaconsumer.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ali akbar azizkhani
 */
@EnableKafka
@Configuration
public class KafkaConfirguration {


    @Bean
    public ConsumerFactory<String,Object> consumerFactory(){

        Map<String,Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        //config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);
        //return new DefaultKafkaConsumerFactory(config,new StringDeserializer(),new JsonDeserializer(User.class));
        return new DefaultKafkaConsumerFactory(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(){

        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        factory.setMessageConverter(new StringJsonMessageConverter());

        return  factory;
    }
}
