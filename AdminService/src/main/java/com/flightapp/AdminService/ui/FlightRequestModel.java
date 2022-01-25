package com.flightapp.AdminService.ui;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.flightapp.AdminService.config.SqlTimeDeserializer;

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
public class FlightRequestModel {
	
		private long airlineId;
		private String flightNumber;
		private String fromLocation;
		private String toLocation;
		private long totalSeat;
		@JsonFormat(pattern = "HH:mm")
		@JsonDeserialize(using = SqlTimeDeserializer.class)
		private Time departureTime;
		@JsonFormat(pattern = "HH:mm")
		@JsonDeserialize(using = SqlTimeDeserializer.class)
		private Time arrivalTime;
		@JsonFormat(pattern = "HH:mm")
		@JsonDeserialize(using = SqlTimeDeserializer.class)
		private Time duration;
		private Double ticketPrice;

}
