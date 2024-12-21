package com.credai.multiapi.utility;


import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.credai.multiapi.entity.NewUser;
import com.credai.multiapi.model.UserDtoFromApi;
import com.credai.multiapi.model.UserDtoFromCsv;
import com.credai.multiapi.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.util.retry.Retry;


@Component
public class ExternalApiCalling {

	Logger logger = LoggerFactory.getLogger(ExternalApiCalling.class);

	public ExternalApiCalling(WebClient externalApiClient) {
		this.externalApiClient = externalApiClient;
	}
	
	
	RedisClient redisClient = RedisClient.create("redis://credai@localhost:6379/");
    StatefulRedisConnection<String, String> connection = redisClient.connect(StringCodec.UTF8);
    RedisCommands<String, String> lettuceTemplate = connection.sync();
    ObjectMapper objMapper = new ObjectMapper();

 	
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RedisTemplate<String, UserDtoFromApi> redisTemplate;
	
	@Autowired
	private Executor taskExecutor;
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private  WebClient externalApiClient;
	
	private Map<String, UserDtoFromApi> zipDetails = new ConcurrentHashMap<String, UserDtoFromApi>();
	int numberOfRetries = 1;
	private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

	
	
	
	

	//getting user from external api using web client and Mono/Flux
	//lettuce
	@Async("taskExecutor")
	public void getUserUsingWebClientLettuceProgram(List<UserDtoFromCsv> userDtoListFromCsv) {
		
		long start = System.currentTimeMillis();

		Flux<NewUser> userFlux = Flux.fromIterable(userDtoListFromCsv)
				.parallel()
//				.runOn(Schedulers.fromExecutor(taskExecutor))
			    .flatMap(userDtoFromCsv -> {
			        String zipcode = userDtoFromCsv.getZipcode();
			

			        if(lettuceTemplate.exists(zipcode)!=0) {
			        	logger.info("{} is executed from redis", zipcode);
			        	NewUser user;
						try {
							user = convertDtoToEntity(objMapper.readValue(lettuceTemplate.get(zipcode),UserDtoFromApi.class),userDtoFromCsv);
							return addDataToDb(user);
						} catch (JsonMappingException e) {
							logger.info(e.getMessage());
						} catch (JsonProcessingException e) {
							logger.info(e.getMessage());
						}
			        	return null;
			        } else {
			            return externalApiClient
			                .get()
			                .uri(zipcode)
			                .retrieve()
			                .bodyToMono(UserDtoFromApi.class)
			                .subscribeOn(Schedulers.fromExecutor(taskExecutor))
			                .publishOn(Schedulers.fromExecutor(taskExecutor))
		                .doOnNext(response -> {
		                	try{
		                		String dtoToJson = objMapper.writeValueAsString(response);
						   		lettuceTemplate.set(zipcode, dtoToJson);
			                    logger.info("{} is executed", zipcode);
		                	}catch (JsonProcessingException e) {
								logger.info("error while processing dto to json, (zip:{})",zipcode);
							}
		                	})
			                .retryWhen(Retry.backoff(numberOfRetries, Duration.ofSeconds(1)))  // Exponential backoff for retries
			                .onErrorResume(e -> {
//			                	checkForError(e);
			                    logger.info("Ignoring user: failed to get response after {} retries (zip: {}) ", numberOfRetries, zipcode );
			                    return Mono.empty();
			                })
			                .map(response -> convertDtoToEntity(response, userDtoFromCsv))
			                .flatMap(user->addDataToDb(user));
			        }
			    })
			    .sequential()
//			    .subscribeOn(Schedulers.fromExecutor(taskExecutor))
			    .onErrorContinue((throwable, o) -> {
			        logger.info("Continuing despite error: " + throwable.getMessage());
			    })
			    .doOnComplete(() -> {
			        long end = System.currentTimeMillis();
			        logger.info("Total time for execution is {}", (end - start));
			        connection.close();
			        redisClient.shutdown();
			    });	
		
        userFlux.collectList().subscribe();
        
//        .subscribe();
//        	  userData -> userRepository.saveAll(userData));
	}    
	

	public Mono<NewUser> addDataToDb(NewUser user){
		return Mono.fromCallable(()->userRepository.save(user));
	}
	
	

	
	
	//getting user from external api using Rest Template and completable future
	//concurrentHashMap
	public void getUserUsingRestTemplateCompletableFuture(List<UserDtoFromCsv> userDtoListFromCsv) {
		long start = System.currentTimeMillis();
		
		 List<CompletableFuture<Void>> futures = userDtoListFromCsv.stream()
		            .map(userDtoFromCsv -> CompletableFuture.supplyAsync(() -> {
		        		long start1 = System.currentTimeMillis();

		                String zipcode = userDtoFromCsv.getZipcode();
				       
		                if (zipDetails.containsKey(zipcode)) {
		                	
		                    return Mono.just(convertDtoToEntity(zipDetails.get(zipcode), userDtoFromCsv));
		                } else {
		                    try {
		                        String uri = "https://zip-api.eu/api/v1/info/US-"+ userDtoFromCsv.getZipcode();
		        				UserDtoFromApi userDtoFromApi = restTemplate.getForObject(uri, UserDtoFromApi.class); 
//		                        logger.info("{} is...{} ",  Thread.currentThread().getName(),(System.currentTimeMillis()-start1));
			                    zipDetails.put(zipcode, userDtoFromApi);
//		                        logger.info("{} is executed {}", zipcode, Thread.currentThread().getName());
		                        NewUser user = convertDtoToEntity(userDtoFromApi, userDtoFromCsv);
//		                        logger.info("{} is......... {} ",  Thread.currentThread().getName(),(System.currentTimeMillis()-start1));
		                        return addDataToDb(user);
		                    } catch (Exception e) {
		                        logger.error("Error retrieving data for zip {}: {}", zipcode, e.getMessage());
		                        return null;  // In case of error, return null
		                    }
		                }
		            }, taskExecutor)
		            .thenAccept(user -> {
		                if (user != null) {
		                    logger.info("Successfully processed user" );
	                        logger.info("{} is thread ",  Thread.currentThread().getName());

		                } else {
		                    logger.info("Ignoring user, something went wrong ");
		                }
		            }))
		            .collect(Collectors.toList());

		        CompletableFuture<Void> allOf = CompletableFuture
		        				.allOf(futures.toArray(new CompletableFuture[0]))
		        				.thenRun(()->{
		        					long end = System.currentTimeMillis();
		        					logger.info("Total time for execution is {}", (end - start));
		        					});
		        
		        allOf.join();  
	    }
	

	
	
	
	public NewUser convertDtoToEntity(UserDtoFromApi userDtoFromApi, UserDtoFromCsv userDtoFromCsv) {
		NewUser user = new NewUser();
		
		user.setFirstName(userDtoFromCsv.getFirstName());
		user.setLastName(userDtoFromCsv.getLastName());
		user.setPhone1(userDtoFromCsv.getPhone1());
		user.setPhone2(userDtoFromCsv.getPhone2());
		user.setEmail(userDtoFromCsv.getEmail());
		user.setWeb(userDtoFromCsv.getWeb());		
		user.setPlaceName(userDtoFromApi.getPlace_name());
		user.setState(userDtoFromApi.getState());
		user.setCountryCode(userDtoFromApi.getCountry_code());
		
		return user;
	}
	
	
	
	
	
	
	
	
	
// below methods are currently not in use	
	
	//getting user from external api using web client and completable future
	//concurrenthashmap
	public void getUserUsingWebClientCompletableFuture(List<UserDtoFromCsv> userDtoListFromCsv) {
		long start = System.currentTimeMillis();
		
		 List<CompletableFuture<Void>> futures = userDtoListFromCsv.stream()
		            .map(userDtoFromCsv -> CompletableFuture.supplyAsync(() -> {
		                String zipcode = userDtoFromCsv.getZipcode();
//                        logger.info("{} ..........Thread........ ",  Thread.currentThread().getName());

		                if (zipDetails.containsKey(zipcode)) {
		                    return Mono.just(convertDtoToEntity(zipDetails.get(zipcode), userDtoFromCsv));
		                } else {
		                    try {
//		                        logger.info("{} Thread........ ",  Thread.currentThread().getName());
		                        UserDtoFromApi userDtoFromApi = externalApiClient
		                            .get()
		                            .uri(zipcode)
		                            .retrieve()
		                            .bodyToMono(UserDtoFromApi.class)
		                            .block();  //.block(REQUEST_TIMEOUT);
//		                        logger.info("{} is... the current thread",  Thread.currentThread().getName());
			                    zipDetails.put(zipcode, userDtoFromApi);
//		                        logger.info("{} is executed in {}", zipcode, Thread.currentThread().getName());
		                        NewUser user = convertDtoToEntity(userDtoFromApi, userDtoFromCsv);
//		                        logger.info("{} is..............{} ",  Thread.currentThread().getName());

		                        return addDataToDb(user);
		                    } catch (Exception e) {
		                        logger.error("{} ; Error retrieving data for zip {}: {}",Thread.currentThread().getName(), zipcode, e.getMessage());
		                        return null;
		                    }
		                }
		            }, taskExecutor)
		            .thenAccept(user -> {
		                if (user != null) {
		                    logger.info("Successfully processed user" );
		                } else {
		                    logger.info("Ignoring user, something went wrong ");
		                }
		            }))
		            .collect(Collectors.toList());

		        CompletableFuture<Void> allOf = CompletableFuture
		        				.allOf(futures.toArray(new CompletableFuture[0]))
		        				.thenRun(()->{
		        					long end = System.currentTimeMillis();
		        					logger.info("Total time for execution is {}", (end - start));
		        					});
		        
		        allOf.join();  
	    }
	
	
	
	
	
//	getting user from external api using web client and Mono/Flux
//	redis
	@Async("taskExecutor")
	public void getUserUsingWebClientReactiveProgram(List<UserDtoFromCsv> userDtoListFromCsv) {
		
		long start = System.currentTimeMillis();
		
		Flux<NewUser> userFlux = Flux.fromIterable(userDtoListFromCsv)
				.parallel()
//				.runOn(Schedulers.fromExecutor(taskExecutor))
			    .flatMap(userDtoFromCsv -> {
			        String zipcode = userDtoFromCsv.getZipcode();

			        if(redisTemplate.hasKey(zipcode)) {
			        	logger.info("{} is executed from redis", zipcode);
			        	NewUser user =convertDtoToEntity((UserDtoFromApi) redisTemplate.opsForValue().get(zipcode),userDtoFromCsv);
			        	return addDataToDb(user);
			        } else {
			            return externalApiClient
			                .get()
			                .uri(zipcode)
			                .retrieve()
			                .bodyToMono(UserDtoFromApi.class)
			                .subscribeOn(Schedulers.fromExecutor(taskExecutor))
			                .publishOn(Schedulers.fromExecutor(taskExecutor))
		                .doOnNext(response -> {
			                	redisTemplate.opsForValue().set(zipcode,response);//, 60, TimeUnit.SECONDS);
			                    logger.info("{} is executed", zipcode);
			                })
			                .retryWhen(Retry.backoff(numberOfRetries, Duration.ofSeconds(1)))  // Exponential backoff for retries
			                .onErrorResume(e -> {
//			                	checkForError(e);
			                    logger.info("Ignoring user: failed to get response after {} retries (zip: {}) ", numberOfRetries, zipcode );
			                    return Mono.empty();
			                })
			                .map(response -> convertDtoToEntity(response, userDtoFromCsv))
			                .flatMap(user->addDataToDb(user));
			        }
			    })
			    .sequential()
//			    .subscribeOn(Schedulers.fromExecutor(taskExecutor))
			    .onErrorContinue((throwable, o) -> {
			        logger.info("Continuing despite error: " + throwable.getMessage());
			    })
			    .doOnComplete(() -> {
			        long end = System.currentTimeMillis();
			        logger.info("Total time for execution is {}", (end - start));
			    });	
		
        userFlux.collectList().subscribe();
        
//        .subscribe();
//        	  userData -> userRepository.saveAll(userData));
	}    
}
