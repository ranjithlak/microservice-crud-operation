package com.example.sevice.repositiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sevice.entity.ClientModel;



@Repository
public interface ClientModelRepositiory extends JpaRepository<ClientModel,Long> {
	
	@Query(value="select *  from client_model c  where c.client_id=?1", nativeQuery = true)
	public ClientModel clientFindById(Long clientId);

}
