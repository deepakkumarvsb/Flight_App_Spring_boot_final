package com.flightapp.UserService.spring.login.security.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightapp.UserService.model.UserEntity;
import com.flightapp.UserService.repo.UserReporsitory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserReporsitory userReporsitory;
	@Autowired
	private ModelMapper modelMapper;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
	  modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	  
	  UserEntity userEntity = userReporsitory.findUserByEmailId(emailId);
	 
	  if(userEntity == null) {
			throw new UsernameNotFoundException("User Not Found with username: " + emailId);
	  }

	  //UserDto userDto = modelMapper.map(userEntity, UserDto.class);
	 return UserDetailsImpl.build(userEntity);
  }
  

}
