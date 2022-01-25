package com.flightapp.UserService.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.UserService.service.UserService;
import com.flightapp.UserService.shared.TicketDto;
import com.flightapp.UserService.shared.UserDto;
import com.flightapp.UserService.ui.TicketRequestModel;
import com.flightapp.UserService.ui.TicketResponseModel;
import com.flightapp.UserService.ui.UserRequestModel;
import com.flightapp.UserService.ui.UserResponseModel;

@RestController
@RequestMapping("/api/v1.0/flight/user")
public class UserController {
	
	@Autowired
	private final UserService userService;
	@Autowired
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserController(UserService userService, ModelMapper modelMapper) {
		super();
		this.userService = userService;
		this.modelMapper = modelMapper;
	}


	@PostMapping("/register")
	public ResponseEntity<UserResponseModel> saveUser(@RequestBody UserRequestModel userRequestModel){
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserResponseModel userResponseModel = userService.saveUser(modelMapper.map(userRequestModel, UserDto.class));
		return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
	}
	
	@PostMapping("/bookTicket")
	public ResponseEntity<TicketResponseModel> bookFlight(@RequestBody TicketRequestModel ticketRequestModel){
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(ticketRequestModel, TicketDto.class);
		TicketResponseModel ticketResponseModel = userService.bookTicket(modelMapper.map(ticketRequestModel, TicketDto.class));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponseModel);
	}
	
	@GetMapping("/bookedTickets/{userId}")
	public ResponseEntity<List<TicketResponseModel>> getAllBookedTicketsByUserId(@PathVariable("userId") long userId){
		
		List<TicketResponseModel> list = new ArrayList<TicketResponseModel>(); 
		
		list=userService.getAllBookedTicketsByUserId(userId);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}
	
	@GetMapping("/flightTickets/{flightId}")
	public ResponseEntity<List<TicketResponseModel>> getAllTicketsFlightByFlightId(@PathVariable("flightId") long flightId){
		
		List<TicketResponseModel> list = new ArrayList<TicketResponseModel>(); 
		
		list=userService.getAllBookedTicketsByFlightId(flightId);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}
	
	@GetMapping("/cancelTicket/{ticketId}")
	public ResponseEntity<TicketResponseModel> cancelTicketByTicketId(@PathVariable("ticketId") long ticketId){
		
		TicketResponseModel ticketResponseModel =userService.cancelByTicketId(ticketId);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(ticketResponseModel);
	}
}
