package com.flightapp.AdminService.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.AdminService.service.AirlineService;
import com.flightapp.AdminService.shared.AirlineDto;
import com.flightapp.AdminService.ui.AirlineRequestModel;
import com.flightapp.AdminService.ui.AirlineResponseModel;

@RestController
@RequestMapping("/api/v1.0/flight/admin/airline")
public class AirlineController {
	
	
	private AirlineService airlineService;
	private ModelMapper modelMapper;
	
	@Autowired
	public AirlineController(AirlineService airlineService, ModelMapper modelMapper) {

		this.airlineService = airlineService;
		this.modelMapper = modelMapper;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<AirlineResponseModel> saveAirline(@RequestBody AirlineRequestModel airlineRequestModel){
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		AirlineDto airlineDto = modelMapper.map(airlineRequestModel, AirlineDto.class);
		
		AirlineResponseModel airlineResponseModel = airlineService.saveAirline(airlineDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(airlineResponseModel);
	}
	
	@GetMapping("/manageAirline")
	public ResponseEntity<List<AirlineResponseModel>> getAllAirlines(){
		
		List<AirlineResponseModel> listAirlineResponseModel = new ArrayList<AirlineResponseModel>(); 
				
		listAirlineResponseModel = airlineService.getAllAirlines();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(listAirlineResponseModel);
		
		
	}

}
