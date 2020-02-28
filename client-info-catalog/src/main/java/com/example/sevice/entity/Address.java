package com.example.sevice.entity;

import java.util.List;

public class Address {
	private Long clientId;
	
	private List<User> clientModel;
	
	public Address() {
		
	}
	

	public Address(Long clientId, List<User> clientModel) {
		super();
		this.clientId = clientId;
		this.clientModel = clientModel;
	}


	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public List<User> getClientModel() {
		return clientModel;
	}

	public void setClientModel(List<User> clientModel) {
		this.clientModel = clientModel;
	}
	
	

}
