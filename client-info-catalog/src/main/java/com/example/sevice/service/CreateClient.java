package com.example.sevice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sevice.entity.Client;
import com.example.sevice.entity.ClientModel;
import com.example.sevice.entity.Clients;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class CreateClient {

	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCreateClient")
	public Long createClient(Client client) {
		Long responseMessage = null;
		try {
			Long clientId = restTemplate.postForObject("http://create-client/api/auth/create-client", client,
					Long.class);

			System.out.println("******************************************" + clientId);

			responseMessage = clientId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}

	public Long getFallbackCreateClient(Client client) {
		return new Long(null);
	}

	@HystrixCommand(fallbackMethod = "getFallbackgetClient")
	public Clients getClient(ClientModel clientModel) {
		Clients responseMessage = null;
		try {
			Client client1 = restTemplate.getForObject(
					"http://create-client/api/auth/get-client/" + clientModel.getClientId(), Client.class);
			Clients client = new Clients(client1.getClientId(), client1.getName(), client1.getEmail(),
					client1.getCity(), clientModel.getAddress1(), clientModel.getAddress2());
			responseMessage = client;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}

	public Clients getFallbackgetClient(ClientModel clientModel) {
		return new Clients(null, null, null, null, clientModel.getAddress1(), clientModel.getAddress2());
	}

	@HystrixCommand(fallbackMethod = "getFallbackgetAllClients")
	public List<Clients> getAllClients(List<ClientModel> clientModel) {
		List<Clients> responseMessage = new ArrayList<>();
		Clients clients = null;
		try {
			System.out.println("*************************************");
			ResponseEntity<List<Client>> client1 = restTemplate.exchange("http://create-client/api/auth/get-clients",
					HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {
					});
			List<Client> client2 = client1.getBody();
			System.out.println("********************************" + client2);
			System.out.println("********************************" + clientModel.size());
			System.out.println("********************************" + client2.size());
			List<ClientModel> list1 = new ArrayList<>(clientModel);
			List<Client> list2 = new ArrayList<>(client2);

			list1.stream()
					.forEach(o1 -> list2.stream().filter(o2 -> o1.getClientId() == o2.getClientId())
							.forEach(o2 -> responseMessage.add(new Clients(o1.getClientId(), o2.getName(),
									o2.getEmail(), o2.getCity(), o1.getAddress1(), o1.getAddress2()))));


		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseMessage;
	}
	public List<Clients> getFallbackgetAllClients(List<ClientModel> clientModel) {
		return new ArrayList<Clients>(null);
	}

}
