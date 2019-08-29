package com.hansai.alldemo.demo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/20
 * @time: 11:08
 */
public class Client2 {

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
            //监听，当f.channel().close()方法完成关闭时，调用group.shutdownGracefully();
            f.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    group.shutdownGracefully();

                }
            });
            //等待服务关闭
            /*
            ChannelFuture sync()：等待，直到异步操作执行完毕，核心思想同await。
            我们得到Future实例后，可以使用sync()方法来阻塞当前线程，直到异步操作执行完毕。
            和await的区别为，如果异步操作失败，那么将会重新抛出异常（将上述cause()方法中的异常抛出）。
            await和sync一样，当异步操作执行完毕后，通过notifyAll()唤醒。
             */
            f.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }


    }


    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {


        Client2 client2 = new Client2();
        //开启一个线程去启动客户端
        new Thread(client2::run).start();
        Thread.sleep(1000);

        while (true) {

            if (client2.channel!=null) {
                    for (int i = 0; i < 100; i++) {
                    client2.channel.writeAndFlush(Unpooled.copiedBuffer(("第" + i + "条").getBytes("UTF-8")));
                }
                Thread.sleep(100);
                break;
            }
        }

        if (client2.channel != null) {
            client2.channel.close();
        }

    }

}
