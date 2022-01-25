package com.flightapp.AdminService.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.AdminService.model.FlightEntity;

@Repository
public interface FlightRepository extends CrudRepository<FlightEntity, Long>{
	
	@Query
	public Iterable<FlightEntity> findByFromLocationAndToLocation(String fromLocation, String toLocation);

}
