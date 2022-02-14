/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jupiterboy.springboot.eventtokafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
class KafkaMessageConsumer {

	@KafkaListener(topics = "test", groupId="123")
	public void consumer(ConsumerRecord consumerRecord){
		Optional<Object> kafkaMassage = Optional.ofNullable(consumerRecord.value());
		if(kafkaMassage.isPresent()){
			Object o = kafkaMassage.get();
			log.info("kafka消费者收到消息：{}",o);
		}
	}

//	@KafkaListener(topicPattern = "us.*", groupId="456")
//	public void listenPattern(ConsumerRecord<String, String> record) {
//		Optional<String> kafkaMessage = Optional.ofNullable(record.value());
//		if (kafkaMessage.isPresent()) {
//			log.info("topic : {}, mes : {}", record.topic(), kafkaMessage.get());
//		}
//	}
}
