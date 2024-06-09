package com.spring.kafkaproducer.example.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.spring.kafkaproducer.example.dto.Customer;

@Service
public class KafkaProducerService {
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	 public void sendMessage(String message) {
		 CompletableFuture<SendResult<String, Object>> future= kafkaTemplate.send("demo-4",1,null,message);
		 future.whenComplete((result,ex) ->{
			 if(ex == null) {
				 System.out.println("Sent message=["+message+"] with offset["+result.getRecordMetadata().offset()+"]"+"] with partition["+result.getRecordMetadata().partition()+"]");
			 }else {
				 System.out.println("Unable to send message=["+message+"] due to :"+ex.getMessage());
			 }
		 });
	 }

		
		 public void sendEventsToObject(Customer customer) {
			 try {
			 CompletableFuture<SendResult<String, Object>> future= kafkaTemplate.send("demoCust",1,null,customer);
			 future.whenComplete((result,ex) ->{
				 if(ex == null) {
					 System.out.println("Sent message=["+customer+"] with offset["+result.getRecordMetadata().offset()+"]"+"] with partition["+result.getRecordMetadata().partition()+"]");
				 }else {
					 System.out.println("Unable to send message=["+customer+"] due to :"+ex.getMessage());
				 }
			 });
			 }catch (Exception e) {
				System.out.println("Error:"+e.getMessage());
			}
		 }
}
