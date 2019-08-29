package com.hansai.alldemo.demo.kafka.config;

import com.hansai.alldemo.demo.kafka.listener.MyProducerListener;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/12
 * @time: 15:30
 */
@Configuration
public class KafkaConsumerConfig {

    @Configuration
    @EnableKafka
    public class KafkaConfig {

        @Bean
        KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>>
        kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            factory.setConcurrency(3);
            factory.getContainerProperties().setPollTimeout(3000);
            //设置consumer 客户端编号
            factory.getContainerProperties().setClientId("hahah-1");
            //设置是否可批量接收消息
            //factory.setBatchListener(Boolean.TRUE);
            return factory;
        }

        @Bean
        public ConsumerFactory<Integer, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(consumerConfigs());
        }

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.1.1.237:9092");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,"true");
            return props;
        }
    }

}
