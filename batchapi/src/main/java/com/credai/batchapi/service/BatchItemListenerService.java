package com.credai.batchapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.credai.batchapi.model.Location;

@Component
public class BatchItemListenerService implements JobExecutionListener{

  private static final Logger log = LoggerFactory.getLogger(BatchItemListenerService.class);
  
	private final JdbcTemplate jdbcTemplate;
	
	long startTime = 0;
	
	public BatchItemListenerService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		startTime = System.currentTimeMillis();
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		 if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
		      log.info("!!! JOB FINISHED! Time to verify the results");

		      jdbcTemplate
		          .query("SELECT id, location, street, city, state, zipcode, lat, lng FROM location", new DataClassRowMapper<>(Location.class))
		          .forEach(loc -> log.info("Found <{{}}> in the database.", loc));
		    }
		 long endTime = System.currentTimeMillis();
		 
		 log.info("total time is : {}", (endTime - startTime));
		  }
}
