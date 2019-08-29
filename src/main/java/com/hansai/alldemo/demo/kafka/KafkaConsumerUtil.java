package com.hansai.alldemo.demo.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/11
 * @time: 15:26
 */
public enum KafkaConsumerUtil {
    instance;
    KafkaConsumerUtil() {
        init();
    }
    private void init() {
        Properties properties = new Properties();
        //服务地址
        properties.setProperty("bootstrap.servers", "192.168.203.128:2181");
        //指定消费模式 latest是指，消费时正好有新消息，则消费刚好到达的消息，否则就消费已有的最新的消息
        properties.setProperty("auto-offset-reset", "latest");
        //消费组名称
        properties.setProperty("group.id", "testkafka");
        //是否自动提交偏移量offset
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(properties);

    }
    private Consumer consumer;
    public Consumer getConsumer() {
        return consumer;
    }
}
