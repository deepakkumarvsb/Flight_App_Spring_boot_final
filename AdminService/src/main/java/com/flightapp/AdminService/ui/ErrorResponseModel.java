package com.flightapp.AdminService.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseModel {

	private String message;
	private Long errorReportingTime;
    private Integer statusCode;
	
}
