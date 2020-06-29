package com.jupiterboy.springboot.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinaConfig {

	@Bean
	public DefaultIoFilterChainBuilder defaultIoFilterChainBuilder(){
		DefaultIoFilterChainBuilder builder = new DefaultIoFilterChainBuilder();
		builder.addLast("logging", new LoggingFilter());
		return builder;
	}
	
	
	@Bean(initMethod="bind",destroyMethod="unbind")
	public NioSocketAcceptor tcpAcceptor(){
		NioSocketAcceptor acceptor = new NioSocketAcceptor();
		acceptor.setDefaultLocalAddress(new InetSocketAddress(7777));
		acceptor.setFilterChainBuilder(defaultIoFilterChainBuilder());
		acceptor.setHandler(new IoHandlerAdapter() {

			@Override
			public void messageReceived(IoSession session, Object message) throws Exception {
				System.out.println("*********************************************");
				if(message instanceof IoBuffer) {
					IoBuffer buffer = (IoBuffer)message;
					System.out.println(new String(buffer.array()));
				}
			}
		});
		return acceptor;
	}
}
