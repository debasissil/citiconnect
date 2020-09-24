package com.mastercard.excercise.citiconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.mastercard.excercise.citiconnect")
public class CitiConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitiConnectApplication.class, args);

	}

}
