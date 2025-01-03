package com.credai.multiapi.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
//@Component
public class FutureConfig {

	   @Bean(name ="taskExecutor")
	     Executor taskExecutor(){
	        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(45);
	        executor.setMaxPoolSize(50);
	        executor.setQueueCapacity(500);
	        executor.setThreadNamePrefix("userThread-");
	        executor.setKeepAliveSeconds(60);
	        executor.initialize();
	        	     
	        return executor;
	    }
	
}
