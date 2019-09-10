package com.hansai.alldemo.demo.rabbitmq.test1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/8/30
 * @time: 19:27
 */
public class ConsumerTest {
    private final static String QUEUE_NAME = "test";
    public void consumer() {
        //test1：创建工厂类，并设置域名/ip 和账号密码
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.203.135");
        factory.setUsername("admin");
        factory.setPassword("admin");

        try {
            //2：通过工厂类获取connection类
            Connection conn = factory.newConnection();
            //3:创建管道
            Channel channel = conn.createChannel();
            //4：指定队列名称
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            //5：定义消息返回回调方法、lamda表达式
            DeliverCallback callback = (consumerTag,delivery)->{
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message+"'");
            };
            //6：获取消息
            channel.basicConsume(QUEUE_NAME,callback,consumertag-> System.out.println());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new ConsumerTest().consumer();
    }

}
