package com.concurrent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ description:
 * @ author: daxiao
 * @ date: 2021/10/18
 */
public class ThreadPerMessage {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(8080));
        while (true) {
            // echo程序
            SocketChannel channel = socketChannel.accept();
            new Thread(() -> {
                try {
                    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                    channel.read(buffer);
                    ByteBuffer flip = (ByteBuffer) buffer.flip();
                    channel.write(flip);
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
