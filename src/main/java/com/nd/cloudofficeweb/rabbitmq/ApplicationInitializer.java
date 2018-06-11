package com.nd.cloudofficeweb.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

@EnableScheduling
@SpringBootApplication
@ComponentScan(value={"com.nd.cloudofficeweb.rabbitmq.*"})
//@Import({Tut2Config.class,Tut2Config.class,Tut3Config.class,Tut4Config.class,Tut5Config.class,Tut6Config.class,Tut7Config.class})
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
