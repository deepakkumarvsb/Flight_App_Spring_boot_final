package com.flightapp.AdminService.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FL_FLIGHT_DTLS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long flightId;
	//@OneToOne
	//@MapsId
	//@JoinColumn(name = "airlineId")
	@Column
	private long airlineId;
	@Column(nullable = false, unique = true, length = 10)
	private String flightNumber;
	@Column(nullable = false)
	private String fromLocation;
	@Column(nullable = false)
	private String toLocation;
	@Column(nullable = false)
	private long totalSeat;
	
	@Column(nullable = false)
	private Time departureTime;
	@Column(nullable = false)
	private Time arrivalTime;
	@Column(nullable = false)
	private Time duration;
	@Column(nullable = false)
	private Double ticketPrice;
	@Column(nullable = false)
	private String status;
	@Column(nullable = false)
	private long statusCode;
}
