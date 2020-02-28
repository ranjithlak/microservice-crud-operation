package com.example.sevice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_model")
public class ClientModel {
	


	@Id
	@Column(name = "client_model_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientModelId;
	
	@Column(name = "client_id")
	private Long clientId;
	
	
	private String address1;
	
	
	private String address2;
	
	public ClientModel() {
		
	}

	public ClientModel(Long clientId, String address1, String address2) {
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
