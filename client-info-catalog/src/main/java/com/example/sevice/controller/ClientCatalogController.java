package com.example.sevice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sevice.entity.Address;
import com.example.sevice.entity.Client;
import com.example.sevice.entity.ClientModel;
import com.example.sevice.entity.Clients;
import com.example.sevice.entity.User;
import com.example.sevice.service.CreateClient;
import com.example.sevice.service.CreateClientModel;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/catalog")
public class ClientCatalogController {
	
	@Autowired
	CreateClient createClients;
	
	@Autowired
	CreateClientModel createClientsModel;
	
	@PostMapping("/create-client")
	public ResponseEntity<?> createClient(@Valid @RequestBody Clients clients){
		ResponseEntity<?> responseMessage=null;
		try {
			Client client = new Client(clients.getName(),clients.getEmail(),clients.getCity());
			Long id =createClients.createClient(client);
			
			ClientModel clientModel=new ClientModel(id,clients.getAddress1(),clients.getAddress2());
			createClientsModel.createClientModel(clientModel);
			responseMessage = new ResponseEntity<>("Create client Sucessfully",HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	
	@GetMapping("/get-client/{clientId}")
	public ResponseEntity<Clients> getClient(@PathVariable("clientId") Long clientId){
		ResponseEntity<Clients> responseMessage=null;
		try {
			
			ClientModel getAddress = createClientsModel.getAddress(clientId);
			System.out.println("************"+getAddress.getAddress1());
			
			Clients response= createClients.getClient(getAddress);
			responseMessage = new ResponseEntity<Clients>(response,HttpStatus.OK);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	
	@GetMapping("/get-clients")
	public ResponseEntity<?> getAllClients(){
		ResponseEntity<?> responseMessage=null;
		try {
			List<ClientModel> getClients =createClientsModel.getClientsModel();
			System.out.println("************"+getClients);
			List<Clients> response = createClients.getAllClients(getClients);
			responseMessage = new ResponseEntity<>(response ,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}

}
