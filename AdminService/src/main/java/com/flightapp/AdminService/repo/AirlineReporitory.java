package com.flightapp.AdminService.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.AdminService.model.AirlineEntity;

@Repository
public interface AirlineReporitory extends CrudRepository<AirlineEntity, Long>{

	
}
