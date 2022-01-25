package com.flightapp.UserService.shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
	
	private long passengerId;
	private String passengerName;
	private String gender;
	private long age;

}
