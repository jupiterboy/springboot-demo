package com.jupiterboy.springboot.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;

public class Gateway {

    private NetServer server;

    public Gateway createGateway(){

        server = Vertx.vertx().createNetServer();
//        server.connectHandler(socket -> {
//            socket.writeHandlerID();
//            NetSocketInternal so = (NetSocketInternal) res;
//            Channel channel = so.channelHandlerContext().channel();
//            ChannelPipeline pipeline = so.channelHandlerContext().pipeline();
//
//            socket.handler(buffer -> {
//                // 在这里应该解析报文，封装为协议对象，并找到响应的处理类，得到处理结果，并响应
//                System.out.println("接收到的数据为：" + buffer.toString());
//
//                // 按照协议响应给客户端
//                socket.write(Buffer.buffer("Server Received"));
//            });
//
//            // 监听客户端的退出连接
//            socket.closeHandler(close -> {
//                System.out.println("客户端退出连接");
//            });
//
//        });

        return null;
    }

    public void start(int port){

        server.listen(port, res -> {
            if (res.succeeded()) {
                System.out.println("服务器启动成功");
            }


        });
    }
}
