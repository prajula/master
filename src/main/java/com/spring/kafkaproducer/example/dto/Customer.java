package com.spring.kafkaproducer.example.dto;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Customer {
	

	private int id;	
	private String name;
	private String email;
	private String contactNo;

}
