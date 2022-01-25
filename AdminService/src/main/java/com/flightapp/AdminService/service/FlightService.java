package com.flightapp.AdminService.service;

import java.util.List;

import com.flightapp.AdminService.shared.FlightDto;
import com.flightapp.AdminService.ui.FlightResponseModel;

public interface FlightService {
	
	public FlightResponseModel saveFlightDetails(FlightDto flightDto);
	
	public List<FlightResponseModel> findByFromLocationAndToLocation(String fromLocation, String toLocation);
	
	public FlightResponseModel getFlightById(long flightId);
	
	public FlightResponseModel blockFlightByFlightId(long flightId);

	public FlightResponseModel unBlockFlightByFlightId(long flightId);

	public FlightResponseModel reScheduleFlight(FlightDto flightDto);

}
