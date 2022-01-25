package com.flightapp.AdminService.service;

import java.util.List;

import com.flightapp.AdminService.shared.AirlineDto;
import com.flightapp.AdminService.ui.AirlineResponseModel;

public interface AirlineService {

	public AirlineResponseModel saveAirline(AirlineDto airlineDto);
	
	public List<AirlineResponseModel> getAllAirlines();
	
}
