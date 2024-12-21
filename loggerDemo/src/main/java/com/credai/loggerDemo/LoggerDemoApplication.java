package com.credai.loggerDemo;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggerDemoApplication 
{

	public static void main(String[] args) {
		SpringApplication.run(LoggerDemoApplication.class, args);
	}
	


}

//implements CommandLineRunner

//private static final Logger logger = LoggerFactory.getLogger(LoggerDemoApplication.class);

//@Override
//public void run(String... args) throws Exception {
//	// TODO Auto-generated method stub
//	logger.info("Application started");
//    logger.debug("Debugging application logic");
//    try {
//        // Simulate some processing
//    } catch (Exception e) {
//        logger.error("Error encountered: {}", e.getMessage());
//    }
//}