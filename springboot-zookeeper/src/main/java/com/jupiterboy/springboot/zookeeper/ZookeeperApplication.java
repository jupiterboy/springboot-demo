package com.jupiterboy.springboot.zookeeper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@SpringBootApplication
public class ZookeeperApplication implements CommandLineRunner {

	@Autowired
	private ZKClient zkClient;

	@Autowired
	private ZKWatcher zkWatcher;

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		zkClient.exists("/zk-watcher-2", zkWatcher);
		Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
			@Override
			public void run() {
				zkClient.updateNode("/zk-watcher-2","TEST-3");
			}
		}, 3L, TimeUnit.SECONDS);


		Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
			@Override
			public void run() {
				zkClient.updateNode("/zk-watcher-2","TEST-5");
			}
		}, 5L, TimeUnit.SECONDS);

		Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
			@Override
			public void run() {
				zkClient.updateNode("/zk-watcher-2","TEST-10");
			}
		}, 10L, TimeUnit.SECONDS);
	}
}
