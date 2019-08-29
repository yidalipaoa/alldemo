package com.hansai.alldemo.demo.kafka;

import com.hansai.alldemo.demo.kafka.interceptor.TestProducerInterceptor;
import org.apache.kafka.clients.producer.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/11
 * @time: 15:26
 */
public enum KafkaProducerUtil {

    instance;

    KafkaProducerUtil() {
        init();

    }

    private void init() {
        Properties properties = new Properties();
        //服务地址
        properties.put("bootstrap.servers", "192.168.203.128:9092");
        //发送消息确认成功模式 0 只要发送就算成功 1 写在leader中就算成功 all 所有集群中都需要写入成功
        properties.put("acks", "all");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//
//        List<ProducerInterceptor<String, String>> interceptors = new ArrayList<>();
//        interceptors.add(new TestProducerInterceptor("1"));
//        interceptors.add(new TestProducerInterceptor("2"));
//        interceptors.add(new TestProducerInterceptor("3"));
//        interceptors.add(new TestProducerInterceptor("4"));
//        interceptors.add(new TestProducerInterceptor("5"));
//        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,interceptors);
        producer = new KafkaProducer<String, String>(properties);
    }

    private Producer producer;


    public Producer getProducer() {
        return producer;
    }




}
