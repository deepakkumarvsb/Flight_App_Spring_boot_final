package com.flightapp.UserService.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.flightapp.UserService.shared.TicketDto;
import com.flightapp.UserService.shared.UserDto;
import com.flightapp.UserService.ui.TicketResponseModel;
import com.flightapp.UserService.ui.UserResponseModel;

public interface UserService {
	
	public UserResponseModel saveUser(UserDto userDto);
	
	public TicketResponseModel bookTicket(TicketDto ticketDto);
	
	public List<TicketResponseModel> getAllBookedTicketsByUserId(long userId);
	
	public List<TicketResponseModel> getAllBookedTicketsByFlightId(long flightId);
	
	public TicketResponseModel cancelByTicketId(long ticketId);
	
	public UserDetails findUserByEmailId(String emailId);
	
	public Boolean existsByEmailId(String emailId);

}
