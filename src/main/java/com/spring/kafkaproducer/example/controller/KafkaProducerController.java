package com.spring.kafkaproducer.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.kafkaproducer.example.dto.Customer;
import com.spring.kafkaproducer.example.service.KafkaProducerService;

@RestController
@RequestMapping("/producer-app")
public class KafkaProducerController {
	
	@Autowired
	private KafkaProducerService service;
	
	@GetMapping(value="/publish/{message}")
	public ResponseEntity<?> publishMessage(@PathVariable String message){
		try {
			for(int i=0;i<=100;i++) {
		service.sendMessage(message+" : "+i);
			}
		return ResponseEntity.ok("Message published successfully");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	@PostMapping("/publish")
	public ResponseEntity<?> sendEvents(@RequestBody Customer customer){
		try {
		service.sendEventsToObject(customer);
		return ResponseEntity.accepted().build();
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
