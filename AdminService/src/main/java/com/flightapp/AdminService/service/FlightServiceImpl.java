package com.flightapp.AdminService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.AdminService.exception.FlightNotFoundException;
import com.flightapp.AdminService.model.FlightEntity;
import com.flightapp.AdminService.repo.FlightRepository;
import com.flightapp.AdminService.shared.FlightDto;
import com.flightapp.AdminService.ui.FlightResponseModel;

@Service
public class FlightServiceImpl implements FlightService {
	
	private final FlightRepository flightRepository;
	private final ModelMapper modelMapper;
	
	
	@Autowired
	public FlightServiceImpl(FlightRepository flightRepository, ModelMapper modelMapper) {

		this.flightRepository = flightRepository;
		this.modelMapper = modelMapper;
	}


	@Override
	public FlightResponseModel saveFlightDetails(FlightDto flightDto) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		flightDto.setStatus("Active");
		flightDto.setStatusCode(110);
		
		FlightEntity flightEntity = flightRepository.save(modelMapper.map(flightDto, FlightEntity.class));
		
		return modelMapper.map(flightEntity, FlightResponseModel.class);
	}


	@Override
	public List<FlightResponseModel> findByFromLocationAndToLocation(String fromLocation, String toLocation) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Iterable<FlightEntity> flightList=flightRepository.findByFromLocationAndToLocation(fromLocation, toLocation);
		List<FlightResponseModel> fligthResponseModelsList=new ArrayList<FlightResponseModel>();
		Iterator<FlightEntity> iterable=flightList.iterator();
		
		while(iterable.hasNext()) {
			fligthResponseModelsList.add(modelMapper.map(iterable.next(), FlightResponseModel.class));
		}
		
		return fligthResponseModelsList;
	}


	@Override
	public FlightResponseModel getFlightById(long flightId) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Optional<FlightEntity> flightEntity = flightRepository.findById(flightId);
		
		FlightResponseModel flightResponseModel = modelMapper.map(flightEntity.get(), FlightResponseModel.class);
		if(flightEntity.isPresent()) {
			return flightResponseModel;
		}else {
			throw new FlightNotFoundException("Flight Not Found for the selected Flight Id : "+flightId);
		}
	}


	@Override
	public FlightResponseModel blockFlightByFlightId(long flightId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Optional<FlightEntity> flightEntity = flightRepository.findById(flightId);

		if(!flightEntity.isPresent()) {
			throw new FlightNotFoundException("Flight Not Found for the selected Flight Id : "+flightId);
		}
		
		FlightEntity flightEntityForUpdate = flightEntity.get();
		
		flightEntityForUpdate.setStatus("Inactive");
		flightEntityForUpdate.setStatusCode(120);
		
		return modelMapper.map(flightRepository.save(flightEntityForUpdate), FlightResponseModel.class);
	}


	@Override
	public FlightResponseModel unBlockFlightByFlightId(long flightId) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Optional<FlightEntity> flightEntity = flightRepository.findById(flightId);

		if(!flightEntity.isPresent()) {
			throw new FlightNotFoundException("Flight Not Found for the selected Flight Id : "+flightId);
		}
		
		FlightEntity flightEntityForUpdate = flightEntity.get();
		
		flightEntityForUpdate.setStatus("Active");
		flightEntityForUpdate.setStatusCode(110);
		
		return modelMapper.map(flightRepository.save(flightEntityForUpdate), FlightResponseModel.class);
	}


	@Override
	public FlightResponseModel reScheduleFlight(FlightDto flightDto) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Optional<FlightEntity> flightEntity = flightRepository.findById(flightDto.getFlightId());

		if(!flightEntity.isPresent()) {
			throw new FlightNotFoundException("Flight Not Found for the selected Flight : "+flightDto.getFlightNumber());
		}
		
		FlightEntity flightEntityForUpdate = flightEntity.get();
		flightEntityForUpdate.setArrivalTime(flightDto.getArrivalTime());
		flightEntityForUpdate.setDepartureTime(flightDto.getDepartureTime());
		flightEntityForUpdate.setDuration(flightDto.getDuration());
		flightEntityForUpdate.setFromLocation(flightDto.getFromLocation());
		flightEntityForUpdate.setTicketPrice(flightDto.getTicketPrice());
		flightEntityForUpdate.setToLocation(flightDto.getToLocation());
		flightEntityForUpdate.setTotalSeat(flightDto.getTotalSeat());
		
		return modelMapper.map(flightRepository.save(flightEntityForUpdate), FlightResponseModel.class);
	}

}
