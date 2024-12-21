package com.credai.multiapi.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.credai.multiapi.exception.CsvParseException;
import com.credai.multiapi.model.UserDtoFromCsv;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class CsvParsing {
	//first_name,last_name,zip,phone1,phone2,email,web => csvformat

	Logger logger = LoggerFactory.getLogger(CsvParsing.class);
	
	public CsvParsing(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}
	
	private final Validator validator;
	
	
	//CsvParisng using apache commons csv
	public List<UserDtoFromCsv> getUserFromCsvUsingCommonsCsv(MultipartFile file) throws IOException{
		
		List<UserDtoFromCsv> userDtoListFromcsv = new ArrayList<>();

		try (InputStreamReader reader = new InputStreamReader(file.getInputStream());
	             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

	            for (CSVRecord record : csvParser) {
	                String firstName = record.get("firstName");
	                String lastName = record.get("lastName");
	                String zipcode = record.get("zipcode");
	                String phone1 = record.get("phone1");
	                String phone2 = record.get("phone2");
	                String email = record.get("email");
	                String web = record.get("web");
	                
	                UserDtoFromCsv userDtoFromCsv = new UserDtoFromCsv(firstName,lastName,zipcode,phone1,phone2,email,web);
	            	try {
	            		Set<ConstraintViolation<UserDtoFromCsv>> violations= validator.validate(userDtoFromCsv);
	            		if(violations.isEmpty()) {
//		            		validateUser(userDtoFromCsv);
			                userDtoListFromcsv.add(userDtoFromCsv);
	            		}else {
	            			violations.parallelStream().forEach((violation)->
	            				logger.info("Ignoring user since: {} {}",violation.getMessage(),(userDtoFromCsv.getZipcode()!=null?"(zip:"+userDtoFromCsv.getZipcode()+")":"")));
	            			
	            		}
	            	}catch(CsvParseException e) {
	            		logger.info(e.getMessage());
	            	}
	            }
	            return userDtoListFromcsv;

	        } catch (IOException e) {
	            logger.info("error occured in csvparsing apache commons");
	            return userDtoListFromcsv;
	        }
	}
	
	
	
	
	//csvparsing using apache opencsv
	public List<UserDtoFromCsv> getUserFromCsvUsingOpenCsv(MultipartFile file) throws IOException{
		
		List<UserDtoFromCsv> userDtoListFromcsv = new ArrayList<>();
		
		try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            CsvToBean<UserDtoFromCsv> csvToBean = new CsvToBeanBuilder<UserDtoFromCsv>(reader)
                    .withType(UserDtoFromCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
//                    .withThrowExceptions(false)
                    .build();
           
            for(UserDtoFromCsv userDto : csvToBean) {
            	try {
            		Set<ConstraintViolation<UserDtoFromCsv>> violations= validator.validate(userDto);
            		if(violations.isEmpty()) {
	            		userDtoListFromcsv.add(userDto);}
            		else {
            			violations.parallelStream().forEach((violation)->
            				logger.info("Ignoring user since: {} {}",violation.getMessage(),(userDto.getZipcode()!=null?"(zip:"+userDto.getZipcode()+")":"")));
            			
            		}
            	}catch(CsvParseException e) {
            		logger.info(e.getMessage());
            	}
            }
            logger.info("{} items satisfy csv validation" , String.valueOf(userDtoListFromcsv.size()));
            return userDtoListFromcsv;
        }
	}
	
	
	
	

	public List<UserDtoFromCsv> getUserFromCsvForRunner() throws IOException{

		String filepath = "C:\\Cred_Ai_WS\\multiapi\\user_details_500.csv";

		List<UserDtoFromCsv> userDtoListFromcsv = new ArrayList<>();

		try (FileReader reader = new FileReader(filepath);
	             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

	            for (CSVRecord record : csvParser) {
	                String firstName = record.get("firstName");
	                String lastName = record.get("lastName");
	                String zipcode = record.get("zipcode");
	                String phone1 = record.get("phone1");
	                String phone2 = record.get("phone2");
	                String email = record.get("email");
	                String web = record.get("web");
	                
	                UserDtoFromCsv userDtoFromCsv = new UserDtoFromCsv(firstName,lastName,zipcode,phone1,phone2,email,web);
	            	try {
	            		Set<ConstraintViolation<UserDtoFromCsv>> violations= validator.validate(userDtoFromCsv);
	            		if(violations.isEmpty()) {
//		            		validateUser(userDtoFromCsv);
			                userDtoListFromcsv.add(userDtoFromCsv);
	            		}else {
	            			violations.parallelStream().forEach((violation)->
	            				logger.info("Ignoring user since: {} {}",violation.getMessage(),(userDtoFromCsv.getZipcode()!=null?"(zip:"+userDtoFromCsv.getZipcode()+")":"")));            			
	            		}
	            	}catch(CsvParseException e) {
	            		logger.info(e.getMessage());
	            	}
	            }
	            return userDtoListFromcsv;
	        } catch (IOException e) {
	            logger.info("error occured in csvparsing apache commons");
	            return userDtoListFromcsv;
	        }
	}
	
		
	
	
	//csvparsing using buffered reader
	public List<UserDtoFromCsv> getUserFromCsv(MultipartFile file) throws IOException{
		List<UserDtoFromCsv> userCsvList = new ArrayList<UserDtoFromCsv>();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while((line=br.readLine())!=null) {
				String[] data = line.split(",");
				UserDtoFromCsv userDtoFromCsv = new UserDtoFromCsv();
				userDtoFromCsv.setFirstName(data[0]);
				userDtoFromCsv.setLastName(data[1]);
				userDtoFromCsv.setPhone1(data[3]);
				userDtoFromCsv.setPhone2(data[4]);
				userDtoFromCsv.setEmail(data[5]);
				userDtoFromCsv.setWeb(data[6]);
				userDtoFromCsv.setZipcode(data[2]);
				userCsvList.add(userDtoFromCsv);
			}
		}catch(Exception e ) {
			e.printStackTrace();
		}
		logger.info("done csv to list");
		return userCsvList;
	}
	
	
	
//	public void validateUser(UserDtoFromCsv userDtoFromCsv) {
//	if(userDtoFromCsv.getZipcode().length()!=5) {
//		throw new CsvParseException("Invalid zipcode, ignoring the current row (zip:"+ (userDtoFromCsv.getZipcode() + ")"));
//	}
//}
}
