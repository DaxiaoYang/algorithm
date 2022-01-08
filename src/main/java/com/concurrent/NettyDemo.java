package com.concurrent;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/25
 * echo服务端
 */
public class NettyDemo {

    /**
     * 用来处理TCP连接请求 监听一个端口
     */
    private static EventLoopGroup bossGroup = new NioEventLoopGroup(1);

    /**
     * 用来处理读写请求 默认 2 * cpu 个 eventloop
     */
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    public static void main(String[] args) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        try {
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 处理读写请求
     */
    private static class EchoServerHandler extends ChannelInboundHandlerAdapter {

        /**
         * 处理读事件
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ctx.write(msg);
        }

        /**
         * 处理读完成事件
         */
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        /**
         * 处理异常事件
         */
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
