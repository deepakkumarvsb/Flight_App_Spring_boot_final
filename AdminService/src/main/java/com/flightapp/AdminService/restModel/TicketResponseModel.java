package com.flightapp.AdminService.restModel;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseModel {
	
	private long ticketId;
	private long flightId;
	private long userId;
	private Date flightDepartureDate;
	private String status;
	private double bookedPrice;

}
