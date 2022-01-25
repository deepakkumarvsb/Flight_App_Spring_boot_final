package com.flightapp.AdminService.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.AdminService.model.AirlineEntity;
import com.flightapp.AdminService.repo.AirlineReporitory;
import com.flightapp.AdminService.shared.AirlineDto;
import com.flightapp.AdminService.ui.AirlineResponseModel;

@Service
public class AirlineServiceImple implements AirlineService{
	
	private AirlineReporitory airlineReporitory;
	private ModelMapper modelMapper;
	
	@Autowired
	public AirlineServiceImple(AirlineReporitory airlineReporitory, ModelMapper modelMapper) {

		this.airlineReporitory = airlineReporitory;
		this.modelMapper = modelMapper;
	}


	@Override
	public AirlineResponseModel saveAirline(AirlineDto airlineDto) {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		AirlineEntity airlineEntity = airlineReporitory.save(modelMapper.map(airlineDto, AirlineEntity.class));
		
		return modelMapper.map(airlineEntity, AirlineResponseModel.class);
	}


	@Override
	public List<AirlineResponseModel> getAllAirlines() {

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Iterable<AirlineEntity> airlineEntity = airlineReporitory.findAll();
		Iterator<AirlineEntity> iterator=airlineEntity.iterator();
		
		List<AirlineResponseModel> list= new ArrayList<AirlineResponseModel>();
		while(iterator.hasNext()) {
			list.add(modelMapper.map(iterator.next(), AirlineResponseModel.class));
		}
		
		return list;
	}

}
