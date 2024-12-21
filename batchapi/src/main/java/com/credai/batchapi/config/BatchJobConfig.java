package com.credai.batchapi.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.credai.batchapi.model.CsvModel;
import com.credai.batchapi.model.Location;
import com.credai.batchapi.service.BatchItemListenerService;
import com.credai.batchapi.service.BatchProcessorService;

@Configuration
public class BatchJobConfig extends DefaultBatchConfiguration {
	
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	
	
	@Bean
	FlatFileItemReader<CsvModel> reader() {
	  return new FlatFileItemReaderBuilder<CsvModel>()
	    .name("batchItemReader")
	    .linesToSkip(1)
	    .resource(new ClassPathResource("sample-data.csv"))
	    .delimited()
	    .names("ID","LOCATION", "ADDRESS", "LAT", "LONG")
	    .targetType(CsvModel.class)
	    .build();
	}

	
	
	@Bean
	BatchProcessorService processor() {
	  return new BatchProcessorService();
	}

	
	
	@Bean
	JdbcBatchItemWriter<Location> writer(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<Location>()
	    .sql("INSERT INTO location (id, location, street, city, state, zipcode, lat, lng) VALUES (:id, :location, :street, :city, :state, :zipcode, :lat, :lng)")
	    .dataSource(dataSource)
	    .beanMapped()
	    .build();
	}


	
	@Bean
	Job importUserJob(JobRepository jobRepository, Step step1, BatchItemListenerService listener) {
	  return new JobBuilder("importUserJob", jobRepository)
	    .listener(listener)
	    .start(step1)
	    .build();
	}

	
	
	@Bean
	Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
	          FlatFileItemReader<CsvModel> reader, BatchProcessorService processor, JdbcBatchItemWriter<Location> writer) {
	  return new StepBuilder("step1", jobRepository)
	    .<CsvModel, Location> chunk(10, transactionManager)
	    .reader(reader)
	    .processor(processor)
	    .writer(writer)
	    .taskExecutor(taskExecutor)
	    .build();
	}
	
	
		
	
}
