package com.flightapp.UserService.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketEntity {

	@Id
	@Column(name = "ticket_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ticketId;
	@Column
	private long flightId;
	@Column
	private long userId;
	@Column
	private String name;
	@Column
	private String emailId;
	@Column
	private long noOfSeats;
	@Column
	private MealType mealType;
	@Column
	private String pnrNumber;
	@Column
	private Date flightDepartureDate;
	@Column
	private String status;
	@Column
	private double bookedPrice;
	@Column
	private long statusCode;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ticket")
	private List<PassengerEntity> passengerDetails;

}
