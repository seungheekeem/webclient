package com.example.webclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class WebclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebclientApplication.class, args);
	}
	@Bean("WebClient")
	public WebClient makeWebClient() {
		return WebClient.builder().build();
	}


	@Bean(name = "threadPoolTaskExecutor")
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(30);
		taskExecutor.setMaxPoolSize(100);
		taskExecutor.setQueueCapacity(20);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setThreadNamePrefix("TaskExecutor");

		return taskExecutor;
	}
}
