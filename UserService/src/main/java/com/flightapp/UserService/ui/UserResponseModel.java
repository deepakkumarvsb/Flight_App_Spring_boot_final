package com.flightapp.UserService.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseModel {
	
	private String firstName;
	private String lastName;
	private String address;
	private Long contactNumber;
	private String emailId;
}
