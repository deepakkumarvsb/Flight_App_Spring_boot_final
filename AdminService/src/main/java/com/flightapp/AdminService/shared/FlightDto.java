package com.flightapp.AdminService.shared;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FlightDto {
	
	private long flightId;
	private long airlineId;
	private String flightNumber;
	private String fromLocation;
	private String toLocation;
	private long totalSeat;
	private Time departureTime;
	private Time arrivalTime;
	private Time duration;
	private Double ticketPrice;
	private String status;
	private long statusCode;

}
