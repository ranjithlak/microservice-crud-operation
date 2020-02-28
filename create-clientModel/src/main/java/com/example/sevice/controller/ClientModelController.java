package com.example.sevice.controller;

import java.util.List;

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

import com.example.sevice.entity.ClientModel;
import com.example.sevice.repositiory.ClientModelRepositiory;



@RestController
@RequestMapping("/api/auth")
public class ClientModelController {
	
	@Autowired
	private ClientModelRepositiory clientModelRepositiory;
	
	@PostMapping("/create-clientModel")
	public ResponseEntity<?> clientModel(@Valid @RequestBody ClientModel client){
		ResponseEntity<?> responseMessage = null;
		try {
			ClientModel newClient = new ClientModel(client.getClientId(),client.getAddress1(),client.getAddress2());
			System.out.println("************************************************************************"+client.getClientId());
			clientModelRepositiory.saveAndFlush(newClient);
			responseMessage = new ResponseEntity<>("Create ClientModel Sucessfully", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	@GetMapping("/get-clientsModel/{clientId}")
	public ResponseEntity<ClientModel> getClientModel(@PathVariable("clientId") Long clientId){
		ResponseEntity<ClientModel> responseMessage = null;
		try {
			System.out.println("*********"+clientId);
			ClientModel getClients =clientModelRepositiory.clientFindById(clientId);
			
			responseMessage = new ResponseEntity<ClientModel>(getClients, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return responseMessage;
	}
	@GetMapping("/get-clientsModel")
	public ResponseEntity<List<ClientModel>> getClientsModel(){
		ResponseEntity<List<ClientModel>> responseMessage = null;
		try {
			List<ClientModel> getClients =clientModelRepositiory.findAll();
			
			responseMessage = new ResponseEntity<List<ClientModel>>(getClients, HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	
	

}
