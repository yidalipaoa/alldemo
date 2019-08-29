package com.hansai.alldemo.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KafkaConsumerUtilTest {
    @Test
    public void getConsumer() throws Exception {

        KafkaConsumer consumer = (KafkaConsumer) KafkaConsumerUtil.instance.getConsumer();
        //指定消费 主题
        consumer.subscribe(Collections.singletonList("testkafka"));

        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
        }
    }
}