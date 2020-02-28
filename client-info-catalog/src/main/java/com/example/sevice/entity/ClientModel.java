package com.example.sevice.entity;

public class ClientModel {
	
    public Long clientId;
	
    public String address1;
	
    public String address2;
	
	public ClientModel() {
		
	}

	public ClientModel(Long clientId, String address1, String address2) {
		super();
		this.clientId = clientId;
		this.address1 = address1;
		this.address2 = address2;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
