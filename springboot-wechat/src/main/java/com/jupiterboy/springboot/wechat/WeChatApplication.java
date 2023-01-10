package com.jupiterboy.springboot.wechat;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@SpringBootApplication
public class WeChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeChatApplication.class, args);
	}


	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {
		WxMpInMemoryConfigStorage config = new WxMpInMemoryConfigStorage();
		config.setAppId("wx4f3c0c7b50459789"); // 设置微信公众号的appid
		config.setSecret("b953bc752b650a917bed314dd4686a1b"); // 设置微信公众号的app corpSecret
		config.setToken("shtmp"); // 设置微信公众号的token
		config.setAesKey("x6RmXTRqmaQx2wruj6Cys8eUyrKNnRXMmsAKXkVjgGc"); // 设置微信公众号的EncodingAESKey
		return config;
	}

	/**
	 * 实例化微信API核心Service对象
	 * @return {@link WxMpService}
	 */
	@Bean
	public WxMpService wxMpService() {
		WxMpService service = new WxMpServiceImpl();
		service.setWxMpConfigStorage(wxMpConfigStorage());
		return service;
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
