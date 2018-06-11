package com.nd.cloudofficeweb.rabbitmq.test1;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"tut1", "hello-queues"})
public class Tut1Config {
	
	@Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    @Profile("receiver")
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Bean
    @Profile("sender")
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}
