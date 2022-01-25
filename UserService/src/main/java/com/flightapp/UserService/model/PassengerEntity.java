package com.flightapp.UserService.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "passenger")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PassengerEntity {

	@Id
	@Column(name = "passenger_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long passengerId;
	@Column
	private String passengerName;
	@Column
	private String gender;
	@Column
	private long age;
	@JoinColumn(name = "ticket_id")
	@ManyToOne(cascade = CascadeType.ALL)
	private TicketEntity ticket;

}
