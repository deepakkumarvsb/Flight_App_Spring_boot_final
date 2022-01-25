package com.flightapp.AdminService.model;

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
@Table(name="FL_AIRLINE_MST")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AirlineEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long airlineId;
	@Column(nullable = false, unique = true, length = 30)
	private String airlineName;
	@Column(nullable = false, length = 10)
	private Long contactNumber;
	@Column(nullable = false, length = 60)
	private String contactAddress;
	

}
