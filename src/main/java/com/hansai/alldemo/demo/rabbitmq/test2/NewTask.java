package com.hansai.alldemo.demo.rabbitmq.test2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/8/29
 * @time: 19:44
 */
public class NewTask {
    private final static String QUEUE_NAME = "test";

    public void test(String argv) {
        //test1：创建工厂类，并设置域名/ip 和账号密码
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.203.135");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        try {
            //2：通过工厂类获取connection类
            Connection conn = connectionFactory.newConnection();
            //3:创建管道
            Channel channel = conn.createChannel();
            //4：指定队列名称
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = String.join(" ", argv);
            //发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        while (input.hasNext()) {
                new NewTask().test(input.next());
        }

    }
}
