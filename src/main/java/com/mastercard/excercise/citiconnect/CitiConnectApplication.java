package com.mastercard.excercise.citiconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.mastercard.excercise.citiconnect")
public class CitiConnectApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CitiConnectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CitiConnectApplication.class, args);

	}

}
