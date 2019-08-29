package com.hansai.alldemo.demo.kafka;

import com.hansai.alldemo.demo.kafka.listener.MyProducerListener;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTestTest {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Test
    public void send() throws Exception {
        //主题
        String topic = "testkafka";
        //键
        String key = "测试kafkatemplate-key1";
        //值
        String value = "测试kafkatemplate-value1";

        ProducerRecord<String,String> record =new ProducerRecord<String,String>(topic,key,value);
        kafkaTemplate.send(record);


    }


    @Test
    public void testCollection() {
        List<Map> list = new ArrayList<Map>();
        Map map = new HashMap();
    }

}