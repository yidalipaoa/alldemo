package com.hansai.alldemo.demo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import java.util.Properties;

public class KafkaProducerUtilTest {


    /**
     * 简单消息
     * @throws Exception
     */
    @Test
    public void getProducer() throws Exception {
        //获取producer
        Producer<String, String> producer = KafkaProducerUtil.instance.getProducer();
        //主题
        String topic = "testkafka2";
        //键
        String key = "tsdfsdfsdfds";
        //值
        String value = "sdfs222233422";
        //创建消息
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,key,value);
        //发送
        producer.send(record,new MyCallback());
        //关闭
//        producer.close();
    }

    @Test
    public void transSend() {
        Properties properties = new Properties();
        //服务地址
        properties.put("bootstrap.servers", "192.168.203.128:9092");
        //********设置事务id
//        properties.put("transactional.id", "my-transactional-id");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        //获取producer
        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        //*********设置事务
//        producer.initTransactions();
        String topic = "testkafka2";//主题
        String key = "testkey";//键
        String value = "sdfsfsdfsf5s465454哈哈";//值
        //*********开始事务
//        producer.beginTransaction();
        //发送
        for(int i=0;i<100;i++){
            //创建消息
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,key+i,value);
            producer.send(record);
        }
        //*********提交事务
//        producer.commitTransaction();
        //关闭
        producer.close();

    }

}