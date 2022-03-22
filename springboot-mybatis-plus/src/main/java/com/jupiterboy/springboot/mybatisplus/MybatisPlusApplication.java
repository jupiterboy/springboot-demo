package com.jupiterboy.springboot.mybatisplus;

import com.jupiterboy.springboot.mybatisplus.entity.ChannelEntity;
import com.jupiterboy.springboot.mybatisplus.entity.CollectorEntity;
import com.jupiterboy.springboot.mybatisplus.parameter.CollectorParameter;
import com.jupiterboy.springboot.mybatisplus.service.ChannelService;
import com.jupiterboy.springboot.mybatisplus.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisPlusApplication implements CommandLineRunner {

	@Autowired
	private ChannelService channelService;

	@Autowired
	private CollectorService collectorService;

	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// 添加采集器
		CollectorEntity collectorEntity = new CollectorEntity();
		collectorEntity.setName("collector-1");
		collectorEntity.setNumber("1");

		collectorEntity.setParameter(CollectorParameter.builder().field1("127").build());

		collectorService.save(collectorEntity);

		ChannelEntity channelEntity = new ChannelEntity();
		channelEntity.setType(1);
		channelEntity.setName("channel-1");
		channelEntity.setNumber("1");

		channelEntity.setCollectorId(collectorEntity.getId());
		channelService.save(channelEntity);

		System.out.println(JsonUtils.toPrettyJson(collectorService.getById("481ee6f72c2f361bfcf99dd13e35858b")));

	}
}
