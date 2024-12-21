package com.credai.batchapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class MultiThreadingConfig {

	   @Bean(name ="taskExecutor")
	     TaskExecutor taskExecutor(){
	        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(25);
	        executor.setMaxPoolSize(50);
	        executor.setQueueCapacity(600);
	        executor.setThreadNamePrefix("userThread-");
	        executor.setKeepAliveSeconds(60);
	        executor.initialize();
	        	     
	        return executor;
	    }
	
}
