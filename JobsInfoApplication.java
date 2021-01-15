package com.example.JobsInfo;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class JobsInfoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsInfoApplication.class, args);
	}


}
