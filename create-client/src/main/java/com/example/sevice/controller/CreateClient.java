package com.example.sevice.controller;

import java.util.List;
import java.util.Optional;

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

import com.example.sevice.model.Client;
import com.example.sevice.repositiory.ClientRepositiory;

@RestController
@RequestMapping("/api/auth")
public class CreateClient {
	
	@Autowired
	private ClientRepositiory clientRepositiory;
	
	
	@PostMapping("/create-client")
	public ResponseEntity<Long> createClient(@Valid @RequestBody Client client) {
		ResponseEntity<Long> responseMessage = null;
		try {
			Client newClient=new Client(client.getName(),client.getEmail(),client.getCity());
			clientRepositiory.saveAndFlush(newClient);
			Long clientId=newClient.getClientId();
			System.out.println("******************************************"+clientId);
			responseMessage = new ResponseEntity<Long>(clientId, HttpStatus.OK);
    	}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
		
	}
	
	@GetMapping("/get-client/{clientId}")
	public ResponseEntity<?> getClient(@PathVariable("clientId") Long clientId){
		ResponseEntity<?> responseMessage = null;
		try {
			Optional<Client> getClients =clientRepositiory.findById(clientId);
			if(getClients == null) {
				responseMessage = new ResponseEntity<>("No Client fOUND", HttpStatus.BAD_REQUEST);	
			}
			responseMessage = new ResponseEntity<>(getClients, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("***********"+responseMessage);
		return responseMessage;
	}
	@GetMapping("/get-clients")
	public ResponseEntity<List<Client> > getClients(){
		ResponseEntity<List<Client> > responseMessage = null;
		try {
			List<Client> getClients =clientRepositiory.findAll();
			responseMessage = new ResponseEntity<List<Client>>(getClients, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}

}
