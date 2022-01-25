package com.flightapp.AdminService.controller;

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
import org.springframework.web.client.RestTemplate;

import com.flightapp.AdminService.restModel.TicketResponseModel;
import com.flightapp.AdminService.restModel.TicketRestResponseModel;
import com.flightapp.AdminService.service.FlightService;
import com.flightapp.AdminService.shared.FlightDto;
import com.flightapp.AdminService.ui.FlightRequestModel;
import com.flightapp.AdminService.ui.FlightResponseModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/api/v1.0/flight/admin")
public class FlightController {

	private final FlightService flightService;
	private final ModelMapper modelMapper;
	private final RestTemplate restTemplate;

	@Autowired
	public FlightController(FlightService flightService, ModelMapper modelMapper, RestTemplate restTemplate) {
		this.flightService = flightService;
		this.modelMapper = modelMapper;
		this.restTemplate = restTemplate;
	}

	@PostMapping("/flight")
	public ResponseEntity<FlightResponseModel> saveFligthDetails(@RequestBody FlightRequestModel flightRequestModel) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightResponseModel flightResponseModel = flightService
				.saveFlightDetails(modelMapper.map(flightRequestModel, FlightDto.class));

		return ResponseEntity.status(HttpStatus.CREATED).body(flightResponseModel);
	}

	@GetMapping("/flight/{fromLocation}/{toLocation}")
	public ResponseEntity<List<FlightResponseModel>> getFlightByFromAndToLocation(
			@PathVariable("fromLocation") String fromLocation, @PathVariable("toLocation") String toLocation) {

		List<FlightResponseModel> list = new ArrayList<FlightResponseModel>();

		list = flightService.findByFromLocationAndToLocation(fromLocation, toLocation);

		return ResponseEntity.status(HttpStatus.FOUND).body(list);
	}

	@GetMapping("/flight/{flightId}")
	@HystrixCommand(fallbackMethod = "handleUserDownTime")
	public ResponseEntity<TicketRestResponseModel> getAllBookedTicketsFromFlightId(
			@PathVariable("flightId") long flightId) {

		TicketRestResponseModel ticketRestResponseModel = new TicketRestResponseModel();

		FlightResponseModel flightResponseModel = flightService.getFlightById(flightId);

		ticketRestResponseModel.setFlightResponseModel(flightResponseModel);

		// List<TicketEntity> list =
		// restTemplate.getForObject("http://localhost:8081/api/v1.0/flight/user/flightTickets/"+flightId,
		// List.class);
		List<TicketResponseModel> list = restTemplate
				.getForObject("http://USER-SERVICE/api/v1.0/flight/user/flightTickets/" + flightId, List.class); // add
																													// @LoadBalanced
																													// bean
		ticketRestResponseModel.setListTicketResponseModel(list);

		return ResponseEntity.status(HttpStatus.OK).body(ticketRestResponseModel);

		// return new ResponseEntity<TicketRestResponseModel>(ticketRestResponseModel,
		// HttpStatus.OK);

	}

	public ResponseEntity<TicketRestResponseModel> handleUserDownTime(@PathVariable("flightId") long flightId) {

		TicketRestResponseModel ticketRestResponseModel = new TicketRestResponseModel();
		FlightResponseModel flightResponseModel = flightService.getFlightById(flightId);

		ticketRestResponseModel.setFlightResponseModel(flightResponseModel);

		return ResponseEntity.status(HttpStatus.OK).body(ticketRestResponseModel);

	}

	@GetMapping("/block/{flightId}")
	public ResponseEntity<FlightResponseModel> blockFlightByFlightId(@PathVariable("flightId") long flightId) {

		FlightResponseModel flightResponseModel = flightService.blockFlightByFlightId(flightId);

		return ResponseEntity.status(HttpStatus.OK).body(flightResponseModel);
	}
	
	@GetMapping("/unblock/{flightId}")
	public ResponseEntity<FlightResponseModel> unBlockFlightByFlightId(@PathVariable("flightId") long flightId) {

		FlightResponseModel flightResponseModel = flightService.unBlockFlightByFlightId(flightId);

		return ResponseEntity.status(HttpStatus.OK).body(flightResponseModel);
	}
	
	@GetMapping("/reSchedule/{flightId}")
	public ResponseEntity<FlightResponseModel> scheduleFlightByFlightId(@RequestBody FlightRequestModel flightRequestModel) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightResponseModel flightResponseModel = flightService.reScheduleFlight(modelMapper.map(flightRequestModel, FlightDto.class));

		return ResponseEntity.status(HttpStatus.CREATED).body(flightResponseModel);
	}

}
