package com.flightapp.UserService.shared;

import java.sql.Date;
import java.util.List;

import com.flightapp.UserService.model.MealType;

import lombok.Data;

@Data
public class TicketDto {
	
	private long ticketId;
	private long flightId;
	private long userId;
	private Date flightDepartureDate;
	private String status;
	private double bookedPrice;
	private long availableSeat;
	private long statusCode;
	private String name;
	private String emailId;
	private long noOfSeats;
	private List<PassengerDto> passengerDetails;
	private MealType mealType;
	private String pnrNumber;

}
