package com.nd.cloudofficeweb.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

import com.nd.cloudofficeweb.rabbitmq.test2.Tut2Config;
import com.nd.cloudofficeweb.rabbitmq.test3.Tut3Config;
import com.nd.cloudofficeweb.rabbitmq.test4.Tut4Config;
import com.nd.cloudofficeweb.rabbitmq.test5.Tut5Config;
import com.nd.cloudofficeweb.rabbitmq.test6.Tut6Config;

@EnableScheduling
@SpringBootApplication
@Import({Tut2Config.class,Tut2Config.class,Tut3Config.class,Tut4Config.class,Tut5Config.class,Tut6Config.class})
public class ApplicationInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

	
	@Value("${spring.profiles.active}")
	private String active;
	
	@Profile("usage_message")
	@Bean
	public CommandLineRunner usage() {
		return new CommandLineRunner() {
			@Override
			public void run(String... arg0) throws Exception {
				System.out.println("This app uses Spring Profiles to control its behavior.\n");
				System.out.println("Sample usage: java -jar  rabbit-tutorials.jar --spring.profiles.active="+active);
			}
		};
	}

	@Profile("!usage_message")
	@Bean
	public CommandLineRunner tutorial() {
		return new RabbitAmqpTutorialsRunner();
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationInitializer.class);
		
		app.run(args);
	}

}
