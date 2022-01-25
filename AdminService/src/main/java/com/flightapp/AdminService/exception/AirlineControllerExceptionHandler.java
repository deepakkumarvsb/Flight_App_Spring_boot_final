package com.flightapp.AdminService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.flightapp.AdminService.ui.ErrorResponseModel;

@RestControllerAdvice
public class AirlineControllerExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponseModel> handleUserNotFoundException(FlightNotFoundException e){
		
		ErrorResponseModel errorResponseModel = new ErrorResponseModel();
		errorResponseModel.setMessage(e.getMessage());
		errorResponseModel.setErrorReportingTime(System.currentTimeMillis());
		errorResponseModel.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseModel);
	}

}
