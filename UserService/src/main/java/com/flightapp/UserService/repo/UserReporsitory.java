package com.flightapp.UserService.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.UserService.model.UserEntity;

@Repository
public interface UserReporsitory extends CrudRepository<UserEntity, Long>{

	public UserEntity findUserByEmailId(String emailId);
	
	Boolean existsByEmailId(String emailId);
}
