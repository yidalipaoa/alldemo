package com.hansai.alldemo.demo.rabbitmq.test2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

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
public class Worker {
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
            //5:一次只接受一条未加密的消息。
            channel.basicQos(1);
            //6：定义消息返回回调方法、lamda表达式
            DeliverCallback callback = (consumerTag,delivery)->{
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message+"'");
                try {
                    Worker.doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            //7：自动返回确认歇息，即读取消息后就马上确认。若执行任务过程出错，不会重新消费。
            boolean autoAck = true;
            //8：获取消息
            channel.basicConsume(QUEUE_NAME,autoAck,callback,consumertag-> System.out.println());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000);
        }
    }

    public static void main(String[] args) {
        new Worker().consumer();
    }

}
