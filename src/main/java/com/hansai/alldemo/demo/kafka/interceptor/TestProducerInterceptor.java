package com.hansai.alldemo.demo.kafka.interceptor;

import com.sun.corba.se.spi.ior.MakeImmutable;
import io.lettuce.core.output.StatusOutput;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/7/3
 * @time: 11:36
 */
public class TestProducerInterceptor implements ProducerInterceptor<String,String> {
    private String mark;
    public TestProducerInterceptor(String mark) {
        this.mark= mark;
    }

    @Override
    public ProducerRecord<String,String> onSend(ProducerRecord<String,String> record) {
        System.out.println("testProducerInterceptor====="+mark);
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
