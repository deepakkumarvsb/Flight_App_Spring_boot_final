package com.flightapp.UserService.ui;

import java.sql.Date;
import java.util.List;

import com.flightapp.UserService.model.MealType;
import com.flightapp.UserService.shared.PassengerDto;

import lombok.Data;

@Data
public class TicketResponseModel {
	
	private long ticketId;
	private long flightId;
	private long userId;
	private Date flightDepartureDate;
	private String status;
	private double bookedPrice;
	private String name;
	private String emailId;
	private long noOfSeats;
	private List<PassengerDto> passengerDetails;
	private MealType mealType;
	private String pnrNumber;

}
