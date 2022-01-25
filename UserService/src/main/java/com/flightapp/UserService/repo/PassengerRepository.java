package com.flightapp.UserService.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.UserService.model.PassengerEntity;
@Repository
public interface PassengerRepository extends CrudRepository<PassengerEntity, Long>{

}
