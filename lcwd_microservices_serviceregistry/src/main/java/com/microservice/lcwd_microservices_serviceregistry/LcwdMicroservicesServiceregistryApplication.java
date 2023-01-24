package com.microservice.lcwd_microservices_serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class LcwdMicroservicesServiceregistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LcwdMicroservicesServiceregistryApplication.class, args);
		System.out.println("-------------------------------");
		System.out.println("Eureka Server started at : 8761");
		System.out.println("-------------------------------");
	}

}
