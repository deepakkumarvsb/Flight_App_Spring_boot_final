package com.flightapp.AdminService.restModel;

import java.util.List;

import com.flightapp.AdminService.ui.FlightResponseModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRestResponseModel {
	
	private FlightResponseModel flightResponseModel;
	private List<TicketResponseModel> listTicketResponseModel;

}
