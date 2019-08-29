package com.hansai.alldemo.demo.netty.client;

import com.hansai.alldemo.demo.netty.common.Utils;
import com.hansai.alldemo.demo.netty.entity.MessageProtocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: Administrator
 * @date: 2019/5/20
 * @time: 16:12
 */
public class Client {

    Channel channel;

    //  运行server
    public void run() {

        final EventLoopGroup group = new NioEventLoopGroup();

        try {
            //Bootstrap 是一个启动 NIO 服务的辅助启动类。你可以在这个服务中直接使用 Channel
            Bootstrap b = new Bootstrap();

            b.group(group)     ////绑定线程组
                    .channel(NioSocketChannel.class)		//指定NIO的模式
                    .handler(new ChannelInitializer<SocketChannel>() { //添加handler
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClinetHandler());
                        }
                    });

            // 链接服务端
            ChannelFuture f = b.connect("127.0.0.1",9999).sync();

            channel = f.channel();
//            String json = JSON.toJSONString(message);
            //Unpooled，是Netty提供的一个用于创建与分配ByteBuf的工具类
            String msg = "1556516416|13141000161|10.1.1.195|666|10000|5555555|www.baidu.com|www.baidu.com/?s=songtao";
//            f.channel().writeAndFlush(Unpooled.copiedBuffer("你好啊".getBytes("UTF-8")));
//            f.channel().closeFuture().sync();


//            f.channel().writeAndFlush(Unpooled.copiedBuffer(msg.getBytes("UTF-8")));
//            f.channel().writeAndFlush(Unpooled.copiedBuffer("你好啊".getBytes("UTF-8")));
//            f.channel().writeAndFlush(message);
            f.channel().closeFuture().sync();
            //关闭服务
//            f.channel().closeFuture().addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    group.shutdownGracefully();
//
//                }
//            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }


    }

    public void write(MessageProtocol messageProtocol) throws UnsupportedEncodingException {
        if(channel!=null){
            channel.write(Unpooled.copiedBuffer(messageProtocol.getMagic().getBytes()));
            channel.write(Unpooled.copiedBuffer(Utils.intToByteArray(messageProtocol.getBodyLength())));
            channel.write(Unpooled.copiedBuffer(Utils.shortToByteArray2(messageProtocol.getType())));
            channel.write(Unpooled.copiedBuffer(messageProtocol.getDeviceId().getBytes()));
            channel.write(Unpooled.copiedBuffer(Utils.intToByteArray(messageProtocol.getLogLength())));
            channel.writeAndFlush(Unpooled.copiedBuffer(messageProtocol.getLog().getBytes("UTF-8")));
        }

    }

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {

//        new Client().run();
        //UTC 时间|手机号|IP地址|业务id|上行流量|下行流量|host|url
//        String data = "1556516419|18611112223|10.1.1.195|666|10000|5555555|www.taobao.com|www.taobao.com/?s=2222";
        //账号日志 UTC 时间|手机号|IP地址|提取源|ID|昵称
        Client client = new Client();
        MessageProtocol messageProtocol = new MessageProtocol();
        //head
        messageProtocol.setMagic("dcba");
        messageProtocol.setBodyLength(15);
        short type = 2;
        messageProtocol.setType(type);
        messageProtocol.setDeviceId("12345678901238");
        String[] source = new String[]{"baidu","taobao","jd","wangyi","ali","sina","tiptok","source1","source2","source3"};
//        client.run();
        new Thread(client::run).start();
        for (int i = 0; i < 10; i++) {
            int number = new Random().nextInt(10);
            String data = "1559699650|18611112223|10.1.1.555|"+source[number]+"|yidalipao" + i + "|意大利炮"+source[number]+i;

            try {
                byte[] log = data.getBytes("UTF-8");
                messageProtocol.setLogLength(log.length);
                messageProtocol.setLog(data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            client.write(messageProtocol);
//            client.channel.closeFuture().sync();
            Thread.sleep(1000);
        }
        client.channel.close();
                    client.channel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    client.channel.close();

                }
            });

    }

    public static void main3(String[] args) {
        short type = 120 ;
        byte [] bytes = Utils.intToByteArray(type);
        byte[] bytes1 = Utils.shortToByteArray2(type);
        System.out.println(bytes1);
        System.out.println(Utils.byteArrayToInt2(bytes1));
    }
}
