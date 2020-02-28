package com.example.sevice.entity;

import java.util.List;

public class Clients {
	
    private Long clientId;
	
	private String name;
	
	private String email;
	
	private String city;
	
    private String address1;
	
	private String address2;
	
	public Clients() {
		
	}

	public Clients(Long clientId, String name, String email, String city, String address1, String address2) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.email = email;
		this.city = city;
		this.address1 = address1;
		this.address2 = address2;
	}


	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	

}
