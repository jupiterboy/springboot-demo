package com.jupiterboy.springboot.zmq;

//
//  Hello World client in Java
//  Connects REQ socket to tcp://localhost:5555
//  Sends "Hello" to server, expects "World" back
//

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.concurrent.TimeUnit;

public class hwclient
{
    public static void main(String[] args)
    {
        try (ZContext context = new ZContext()) {
            //  Socket to talk to server
            System.out.println("Connecting to hello world server");

//            ZMQ.Socket socket = context.createSocket(SocketType.SUB);
//            socket.connect("tcp://localhost:5555");
//
//            for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
//                String request = "Hello";
//                System.out.println("Sending Hello " + requestNbr);
//                socket.send(request.getBytes(ZMQ.CHARSET), 0);
//
//                byte[] reply = socket.recv(0);
//                System.out.println(
//                    "Received " + new String(reply, ZMQ.CHARSET) + " " +
//                    requestNbr
//                );
//            }

            ZMQ.Socket socket = context.createSocket(SocketType.PUB);
            socket.bind("tcp://*:5555");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int requestNbr = 0; requestNbr != 10; requestNbr++) {
                String request = "topic.zeromq::ChannelValueEvent::{\"timestamp\":1650531178074,\"id\":\"8bbf72c3-94bc-401f-a514-4354d10df057\",\"name\":\"ChannelValueEvent\",\"sourceId\":\"4028b2f0804aad0201804aad2be50001\",\"sourceName\":\"采集器: 1, 通道: 1\",\"desc\":\"ID: 8bbf72c3-94bc-401f-a514-4354d10df057\",\"channelValue\":{\"type\":0,\"vibrationValue\":{\"value1\":0.5007819215266253,\"value2\":0.08052650791584559},\"timestamp\":\"2022-04-15 12:41:30\"}}";
                    System.out.println(request);
                socket.send(request.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}
