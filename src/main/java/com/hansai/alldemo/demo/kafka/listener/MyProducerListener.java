package com.hansai.alldemo.demo.kafka.listener;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/12
 * @time: 15:49
 */
public class MyProducerListener<K, V> implements ProducerListener<K, V> {
    @Override
    public void onSuccess(ProducerRecord<K, V> producerRecord, RecordMetadata recordMetadata) {
        System.out.println("测试producerListener");
        System.out.println(producerRecord.key()+":"+producerRecord.value());
    }

    @Override
    public void onSuccess(String topic, Integer partition, K key, V value, RecordMetadata recordMetadata) {

    }

    @Override
    public void onError(ProducerRecord<K, V> producerRecord, Exception exception) {

    }

    @Override
    public void onError(String topic, Integer partition, K key, V value, Exception exception) {

    }
}
