package com.hansai.alldemo.demo.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/5/20
 * @time: 15:29
 */
public class Server {

    int port;

    public Server(int port) {
        this.port = port;
    }

    //  运行server
    public void run() {

        // 是用来处理I/O操作的多线程事件循环器
        // 用来接收进来的连接
        final EventLoopGroup bossGroup = new NioEventLoopGroup();
        //用来处理已经被接收的连接
        final EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //ServerBootstrap 是一个启动 NIO 服务的辅助启动类。你可以在这个服务中直接使用 Channel
            ServerBootstrap b = new ServerBootstrap(); // (2)

            b.group(bossGroup, workerGroup)     ////绑定俩个线程组
                    .channel(NioServerSocketChannel.class)        //指定NIO的模式
                    .childHandler(new ChannelInitializer<SocketChannel>() { //添加handler
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    })
                    .option(ChannelOption.AUTO_READ,true)
                    .option(ChannelOption.SO_BACKLOG, 128)   // 指定tcpip缓冲区
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // 保持连接
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();

            f.channel().closeFuture().sync();

            //关闭服务
//            f.channel().closeFuture().addListener(new ChannelFutureListener() {
//                @Override
//                public void operationComplete(ChannelFuture future) throws Exception {
//                    bossGroup.shutdownGracefully();
//                    workerGroup.shutdownGracefully();
//                }
//            });
//            //关闭通道
//            f.channel().close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        new Server(9999).run();
    }



}
