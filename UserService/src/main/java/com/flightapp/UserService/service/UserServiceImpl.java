package com.flightapp.UserService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flightapp.UserService.model.TicketEntity;
import com.flightapp.UserService.model.UserEntity;
import com.flightapp.UserService.repo.PassengerRepository;
import com.flightapp.UserService.repo.TicketRepository;
import com.flightapp.UserService.repo.UserReporsitory;
import com.flightapp.UserService.shared.TicketDto;
import com.flightapp.UserService.shared.UserDto;
import com.flightapp.UserService.ui.TicketResponseModel;
import com.flightapp.UserService.ui.UserResponseModel;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserReporsitory userReporsitory;
	@Autowired
	public TicketRepository ticketRepository;
	@Autowired
	public PassengerRepository passengerRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	public UserServiceImpl(UserReporsitory userReporsitory, TicketRepository ticketRepository,
			PassengerRepository passengerRepository, ModelMapper modelMapper, PasswordEncoder encoder) {
		this.userReporsitory = userReporsitory;
		this.ticketRepository = ticketRepository;
		this.passengerRepository = passengerRepository;
		this.modelMapper = modelMapper;
		this.encoder = encoder;
	}


	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserEntity userEntity=userReporsitory.findUserByEmailId(emailId);
		
		if(userEntity == null) {
			throw new UsernameNotFoundException("User Not found in the database");
		}
		UserResponseModel responseModel = modelMapper.map(userEntity, UserResponseModel.class);
		return new User(responseModel.getEmailId(),responseModel.getEmailId(),new ArrayList<>());
		
	} 
	

	@Override
	public UserResponseModel saveUser(UserDto userDto) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		userDto.setPassword(encoder.encode(userDto.getPassword()));
		UserEntity userEntity=userReporsitory.save(modelMapper.map(userDto, UserEntity.class));
		
		return modelMapper.map(userEntity, UserResponseModel.class);
	}

	@Override
	public TicketResponseModel bookTicket(TicketDto ticketDto) {

		String randomString = usingUUID(); 
		String pnrNumber = randomString.substring(0, 8);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		TicketEntity ticketEntity = modelMapper.map(ticketDto, TicketEntity.class);
		TicketEntity ticketPnrDetailsBean = ticketRepository.findByPnrNumber(pnrNumber);
		if(ticketPnrDetailsBean != null){
			while(ticketPnrDetailsBean.getPnrNumber() == pnrNumber) {
				randomString = usingUUID(); 
				pnrNumber = randomString.substring(0, 8);
			}
		}
		ticketEntity.setPnrNumber(pnrNumber);
		
		ticketEntity.getPassengerDetails().forEach(pass->pass.setTicket(ticketEntity));
		return modelMapper.map(ticketRepository.save(ticketEntity), TicketResponseModel.class);
	}

	@Override
	public List<TicketResponseModel> getAllBookedTicketsByUserId(long userId) {
		
		final long statusUserCreated = 10;
		
		Iterable<TicketEntity> entity = ticketRepository.findByUserIdAndStatusCode(userId, statusUserCreated);
		
		Iterator<TicketEntity> iterator=entity.iterator();
		
		List<TicketResponseModel> list= new ArrayList<TicketResponseModel>();
		while(iterator.hasNext()) {
			list.add(modelMapper.map(iterator.next(), TicketResponseModel.class));
		}
		
		return list;
	}

	@Override
	public List<TicketResponseModel> getAllBookedTicketsByFlightId(long flightId) {
		
		Iterable<TicketEntity> entity = ticketRepository.findByFlightId(flightId);
		
		Iterator<TicketEntity> iterator=entity.iterator();
		
		List<TicketResponseModel> list= new ArrayList<TicketResponseModel>();
		while(iterator.hasNext()) {
			list.add(modelMapper.map(iterator.next(), TicketResponseModel.class));
		}
		
		return list;
	}

	@Override
	public TicketResponseModel cancelByTicketId(long ticketId) {

		TicketEntity ticketEntity = ticketRepository.findByTicketId(ticketId);
		
		if(ticketEntity==null) {
			//Ticket Not Found Exception
		}
		
		ticketEntity.setStatusCode(20);
		ticketEntity.setStatus("Cancelled");
		
		TicketEntity entity = ticketRepository.save(ticketEntity);
		
		return modelMapper.map(entity, TicketResponseModel.class);
	}

	 private String usingUUID() { 
		    UUID randomUUID = UUID.randomUUID(); 
		    return randomUUID.toString().replaceAll("-", ""); 
		  }

	@Override
	public UserDetails findUserByEmailId(String emailId) {
		UserEntity userEntity=userReporsitory.findUserByEmailId(emailId);
		
		if(userEntity == null) {
			//User Not Found Exception
		}
		UserResponseModel responseModel = modelMapper.map(userEntity, UserResponseModel.class);
		return new User(responseModel.getEmailId(),responseModel.getEmailId(),new ArrayList<>());
		
	}


	@Override
	public Boolean existsByEmailId(String emailId) {
		
		UserEntity userEntity=userReporsitory.findUserByEmailId(emailId);
		if(userEntity != null) {
			return true;
		}else {
			return false;
		}
	}
	
	

	 
}
