package com.hansai.alldemo.demo.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/8/29
 * @time: 19:44
 */
public class ProducerTest {
    private final static String QUEUE_NAME = "hello";

    public void test() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.203.134");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        try {
            Connection conn = connectionFactory.newConnection();
            Channel channel = conn.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        new ProducerTest().test();
    }
}
