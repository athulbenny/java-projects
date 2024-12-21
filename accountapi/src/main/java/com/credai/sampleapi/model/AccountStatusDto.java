package com.credai.sampleapi.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AccountStatusDto {
	private long custId;
	private String status;
}
