package com.credai.batchapi.service;

import java.io.StringReader;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.credai.batchapi.exception.ClientErrorException;
import com.credai.batchapi.exception.ServerErrorException;
import com.credai.batchapi.exception.ZipCodeResponseException;
import com.credai.batchapi.model.CityStateLookupResponse;
import com.credai.batchapi.model.CsvModel;
import com.credai.batchapi.model.Location;
import com.credai.batchapi.model.ZipCode;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshalException;
import jakarta.xml.bind.Unmarshaller;

@Component
@EnableAsync
public class BatchProcessorService implements ItemProcessor<CsvModel, Location>{

	@Value("${usps.api.base-url}")
	private String baseUrl;
		
	@Value("${usps.api.user-id}")
	private String userId;
	
	private static final Logger log = LoggerFactory.getLogger(BatchProcessorService.class);

	AtomicInteger randValue = new AtomicInteger();
	
	RestTemplate restTemplate = new RestTemplate();
	
	Map<String, CityStateLookupResponse> zipCodeToCityStateLookupMap = new ConcurrentHashMap<String, CityStateLookupResponse>();
	
	
	
	@Override
	public Location process(CsvModel item) throws Exception {
		
		String address = item.ADDRESS().toUpperCase();
		int lengthOfAddress = address.length();
		String zip = address.substring(lengthOfAddress-5, lengthOfAddress);
		String addressWithoutZip = address.replace(zip, "");
		String ID = item.ID();
		String LAT = item.LAT();
		String LONG = item.LONG();
		CityStateLookupResponse cityStateLookupResponse = null;
		
		
		try {
			if(LAT == null || LAT.isEmpty() || LAT.isBlank()) {
				throw new ClientErrorException("Latitude value not supported");
			}
		}catch(ClientErrorException e) {
			log.info("Converting null/Empty Latitude to 0.0");
			LAT="0.0";
		}
		
		
		try {
			if(LONG==null || LONG.isEmpty() || LONG.isBlank()) {
				throw new ClientErrorException("Longitude value not supported");
			}
		}catch(ClientErrorException e) {
			log.info("Converting null/Empty Longitude to 0.0");
			LONG = "0.0";
		}
		
		
		LONG = Double.toString(Double.parseDouble(LONG)*(3.14/180));
		LAT = Double.toString(Double.parseDouble(LAT)*(3.14/180));
		
		
		try {
			if(ID.isEmpty() || ID.isBlank() || ID == null) {
				throw new ClientErrorException("ID cannot be null");
			}
		}catch(ClientErrorException e) {
			log.info("Converting null ID to a unique one");
			int randomIntValue = randValue.addAndGet((int)System.currentTimeMillis());
			randomIntValue = (randomIntValue<0)? -randomIntValue : randomIntValue;
			ID = "CRED" + randomIntValue;
		}
		
		
		try {
			
			if(!zipCodeToCityStateLookupMap.containsKey(zip)) {
				String URL = baseUrl + userId + "'><ZipCode ID= '0'><Zip5>" + zip + "</Zip5></ZipCode></CityStateLookupRequest>";
				String urlResponse = restTemplate.getForObject(URL, String.class);
				if(urlResponse == null)  throw new ServerErrorException("Internal Server Error");
				cityStateLookupResponse = convertXmlToJava(urlResponse, zip);				
			}
			else cityStateLookupResponse = zipCodeToCityStateLookupMap.get(zip);
			
			ZipCode zipcode = cityStateLookupResponse.getZipCode();
			
			if(zipcode.getError() != null) {
				throw new ZipCodeResponseException(new Date(), zipcode.getError().getDescription());
			}
			
			String addressWithoutState = addressWithoutZip.replace(zipcode.getState(),"");
			String street = addressWithoutState.replace(zipcode.getCity(),"");
					
			Location location = new Location(
					ID,
					item.LOCATION(),
					street,
					cityStateLookupResponse.getZipCode().getCity(),
					cityStateLookupResponse.getZipCode().getState(),
					zip,
					LAT,
					LONG	);		
			
			return location;
			}
		
			catch(ZipCodeResponseException zipCodeResponseException) {
				log.info(zipCodeResponseException.getError());
				return null;
			}
		
			catch(NullPointerException n) {
				log.info("null pointer exception is thrown");
				return null;
			}
		
			catch(JAXBException e) {
				log.info("JAXB exception is caught");
				return null;
			}
		
			catch(ServerErrorException serverErrorException) {
				log.info("external api calling failed");
				return null;
			}
	}
	
	
	
	
	public CityStateLookupResponse convertXmlToJava(String xml, String zip) throws JAXBException{
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CityStateLookupResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			CityStateLookupResponse cityStateLookupResponse = (CityStateLookupResponse)unmarshaller.unmarshal(new StringReader(xml));
			zipCodeToCityStateLookupMap.put(zip, cityStateLookupResponse);
			return cityStateLookupResponse;
		}
		
		catch(UnmarshalException e) {	
			log.info("unmarshaller exception is caught");
			return null;
		}
		
		catch(JAXBException e) {
			log.info("JAXB exception is caught");
			return null;
		}
	}

	
}
