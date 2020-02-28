package com.example.sevice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.sevice.entity.Address;
import com.example.sevice.entity.Client;
import com.example.sevice.entity.ClientModel;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@Service
public class CreateClientModel {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod ="getFallbackcreateClientModel")
	public String createClientModel(ClientModel clientModel) {
		String responseMessage = null;
		try {
			ClientModel clientModels = restTemplate.postForObject("http://create-clientModel/api/auth/create-clientModel",clientModel,ClientModel.class);
			responseMessage ="created";
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	
	public String getFallbackcreateClientModel(ClientModel clientModel) {
		return new String();
	}
	
	@HystrixCommand(fallbackMethod ="getFallbackgetAddress")
	public ClientModel getAddress(@PathVariable("clientId") Long clientId) {
		ClientModel responseMessage = null;
		try {
			ResponseEntity<ClientModel> address =restTemplate.getForEntity("http://create-clientModel/api/auth/get-clientsModel/"+clientId,ClientModel.class);
			ClientModel address1 =address.getBody(); 
			
	       System.out.println("******************************************************************"+address1);
	       responseMessage = address1;
	    
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	
	public ClientModel getFallbackgetAddress(@PathVariable("clientId") Long clientId) {
		return new ClientModel(null,null,null);
	}
	
	@HystrixCommand(fallbackMethod ="getFallbackgetClientsModel")
	public List<ClientModel> getClientsModel(){
		 List<ClientModel> responseMessage = null;
		 try {
			ResponseEntity<List<ClientModel>> getClients = restTemplate.exchange("http://create-clientModel/api/auth/get-clientsModel/",HttpMethod.GET, null,new ParameterizedTypeReference<List<ClientModel>>() {});
			List<ClientModel> getClients1 = getClients.getBody();
			responseMessage = getClients1;
			System.out.println("********************************"+responseMessage);
			
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return responseMessage;
	}
	
	public List<ClientModel> getFallbackgetClientsModel() {
		return new ArrayList<ClientModel>(null);
	}
}
