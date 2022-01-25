package com.flightapp.AdminService.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AirlineDto {
	
	private long airlineId;
	private String airlineName;
	private long contactNumber;
	private String contactAddress;

}
