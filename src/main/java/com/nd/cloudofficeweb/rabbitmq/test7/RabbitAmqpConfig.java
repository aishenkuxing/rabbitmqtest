package com.nd.cloudofficeweb.rabbitmq.test7;

import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({ "tut7", "userdefined" })
public class RabbitAmqpConfig {
	@Bean
	public TopicExchange workflowTopic() {
		return new TopicExchange("amq.topic");
	}
	
	@Profile("receiver")
	private static class ReceiverConfig {

		@Bean
		public Queue autoDeleteQueue1() {
		     return new AnonymousQueue();
		}
		
		@Bean
		public RabbitWorkFlowReceiver receiver() {
			return new RabbitWorkFlowReceiver();
		}

		@Bean
		public Queue workflowQueue() {
			Queue queue = new Queue("hello");
			return queue;
		}

		@Bean
		public Binding binding(TopicExchange workflowTopic, Queue workflowQueue) {
			return BindingBuilder.bind(workflowQueue).to(workflowTopic).with("*.workflow.*");
		}
	}

	@Profile("sender")
	@Bean
	public RabbitWorkFlowSender sender() {
		return new RabbitWorkFlowSender();
	}
}
