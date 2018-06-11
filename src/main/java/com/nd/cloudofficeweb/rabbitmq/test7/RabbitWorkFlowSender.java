package com.nd.cloudofficeweb.rabbitmq.test7;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class RabbitWorkFlowSender {
	
	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	private int index;

	private int count;

	private final String[] keys = { "quick.workflow.fox" };

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		StringBuilder builder = new StringBuilder("Hello to ");
		if (++this.index == keys.length) {
			this.index = 0;
		}
		String key = keys[this.index];
		builder.append(key).append(' ');
		builder.append(Integer.toString(++this.count));
		String message = builder.toString();
		
		
		

//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.245.120",5672);
//		connectionFactory.setUsername("root");
//		connectionFactory.setPassword("root");
//		connectionFactory.setPublisherConfirms(true);
//		connectionFactory.setVirtualHost("/");
//		template.setConnectionFactory(connectionFactory);
		
		template.convertAndSend(topic.getName(), key, message);
		System.out.println(" [x] Sent '" + message + "'");
	}
}
