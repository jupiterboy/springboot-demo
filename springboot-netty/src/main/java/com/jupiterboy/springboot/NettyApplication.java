package com.jupiterboy.springboot;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

@SpringBootApplication
@ServletComponentScan
@Configuration
public class NettyApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(NettyApplication.class);

	public static final int TYPE_TEMPERATURE_HUMIDITY = 1;
	public static final int TYPE_OXYGEN = 2;

	private static Map<String, Integer> maps = new HashMap<String, Integer>();
	static{
		maps.put("123DADEF5B47", TYPE_TEMPERATURE_HUMIDITY);
		maps.put("123DADEFA0C3", TYPE_TEMPERATURE_HUMIDITY);
		maps.put("164595ED0CA2", TYPE_OXYGEN);
		maps.put("0F69583347E3", TYPE_OXYGEN);
	}

	public static Map<String, Queue<Object>> queues = new HashMap<String, Queue<Object>>();
	static{
		queues.put("123DADEF5B47", new ArrayBlockingQueue<Object>(10000));
		queues.put("123DADEFA0C3", new ArrayBlockingQueue<Object>(10000));
		queues.put("164595ED0CA2", new ArrayBlockingQueue<Object>(10000));
		queues.put("0F69583347E3", new ArrayBlockingQueue<Object>(10000));
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Autowired
	private OneWebSocket oneWebSocket;

	public static void main(String[] args) {
		SpringApplication.run(NettyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Application is starting...");
		EventLoopGroup group = new NioEventLoopGroup(); // (1)

		try {
			Bootstrap b = new Bootstrap(); // (2)
			b.group(group)
				.channel(NioDatagramChannel.class)
					.option(ChannelOption.SO_BROADCAST, true)
				.handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ChannelPipeline pipeline = ch.pipeline();
						pipeline.addLast(new UDPDecoder());
						UDPHandler udpHandler = new UDPHandler();
						udpHandler.setOneWebSocket(oneWebSocket);
						pipeline.addLast(udpHandler);
					}

				});

			ChannelFuture cf = b.bind(8888).sync();
			logger.info("Application started!");
			cf.channel().closeFuture().sync();
		} catch(InterruptedException e){
			e.printStackTrace();
		}finally {
			group.shutdownGracefully();
		}
	}

	private static class TemperatureHumidityData extends SensorData {
		public double temperature;
		public double humidity;

		@Override
		public String toString() {
			return "TemperatureHumidityData {" +
					"temperature=" + temperature +
					", humidity=" + humidity +
					", clientId=" + clientId +
					", timestamp=" + timestamp +
					'}';
		}
	}

	private static class SensorData {
		public String clientId;
		public final Date timestamp = new Date();
	}

	private static class OxygenData extends SensorData{
		public double oxygen;

		@Override
		public String toString() {
			return "SensorData {" +
					"oxygen=" + oxygen +
					", clientId=" + clientId +
					", timestamp=" + timestamp +
					'}';
		}
	}

	private static class UDPMessage {
		public String clientId;
		public Object message;
		public byte[] data = new byte[0];
		public final Date timestamp = new Date();

		public String ip;
		public int port;

		@Override
		public String toString() {
			return "UDPMessage{" +
					"clientId='" + clientId + '\'' +
					", message=" + message +
					", data=" + ByteUtils.toHex(data) +
					", timestamp=" + timestamp +
					", ip=" + ip +
					", port=" + port +
					'}';
		}
	}

	@Getter
	@Setter
	private static class UDPHandler extends SimpleChannelInboundHandler<UDPMessage> {

		private static final Logger logger = LoggerFactory.getLogger(UDPHandler.class);

		private OneWebSocket oneWebSocket;

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, UDPMessage msg) throws Exception {
			logger.info("Message Received:{}", msg.toString());
//			ctx.writeAndFlush(new DatagramPacket(Unpooled.copiedBuffer(msg.data), new InetSocketAddress(msg.ip, msg.port)));
			if(oneWebSocket != null){
				oneWebSocket.sendMessage(msg.message.toString());
			}
			queues.get(msg.clientId).add(msg.message);
//			System.out.println(queues.get(msg.clientId));
		}
	}

	private static class UDPDecoder extends MessageToMessageDecoder<DatagramPacket> {

		private final static Logger logger = LoggerFactory.getLogger(UDPDecoder.class);

		@Override
		protected void decode(ChannelHandlerContext ctx, DatagramPacket packet, List<Object> out) throws Exception {
			ByteBuf in = packet.content();
			byte[] frame = readFrame(in);
			logger.debug("Frame:{}", ByteUtils.format(frame));

			int command = getCommand(frame);
			if(command != 0x03){
				return;
			}
			if(frame != null){
				UDPMessage msg = decode(frame);
				if(msg != null){
					msg.ip = packet.sender().getHostString();
					msg.port = packet.sender().getPort();
					logger.debug("Message:{}", msg.message);

					out.add(msg);
				}
			}
		}

		private byte[] readFrame(ByteBuf in) {
			int len = in.readableBytes();
			byte[] frame = new byte[len];
			in.readBytes(frame);
			return frame;
		}

		private UDPMessage decode(byte[] frame) {
			UDPMessage udpMessage = new UDPMessage();
			udpMessage.data = frame;
			udpMessage.clientId = getDeviceId(frame);
			logger.debug("Device ID:{}", udpMessage.clientId);
			if(maps.get(udpMessage.clientId) == TYPE_TEMPERATURE_HUMIDITY){
				udpMessage.message = getTemperatureHumidityData(frame);
			}
			if(udpMessage.clientId.equals("0F69583347E3")){
				udpMessage.message = getOxygenData(frame);
			}
			((SensorData)udpMessage.message).clientId = udpMessage.clientId;
			return udpMessage;
		}

		private int getCommand(byte[] frame){
			ByteBuffer buf = ByteBuffer.wrap(frame);
			buf.position(13);
			return buf.get();
		}

		private String getDeviceId(byte[] frame){
			byte[] tmp = new byte[6];
			ByteBuffer buf = ByteBuffer.wrap(frame);
			buf.position(3);
			buf.get(tmp);
			return ByteUtils.toHex(tmp, "");
		}

		private TemperatureHumidityData getTemperatureHumidityData(byte[] frame){
			ByteBuffer buf = ByteBuffer.wrap(frame);
			buf.position(14);
			int length = buf.getShort();
			logger.debug("Length:{}", length);

			TemperatureHumidityData sensorData = new TemperatureHumidityData();
			sensorData.temperature = buf.getInt()/10.0;
			sensorData.humidity = buf.getInt()/10.0;
			return sensorData;
		}

		private OxygenData getOxygenData(byte[] frame){
			ByteBuffer buf = ByteBuffer.wrap(frame);
			buf.position(14);
			int length = buf.getShort();
			logger.debug("Length:{}", length);

			OxygenData sensorData = new OxygenData();
			sensorData.oxygen = buf.getInt()/10.0;
			return sensorData;
		}
		
	}
}
