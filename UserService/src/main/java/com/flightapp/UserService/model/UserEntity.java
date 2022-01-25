package com.flightapp.UserService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "FL_USER_MST")

public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	@Column(nullable = false, length = 60)
	private String firstName;
	@Column(nullable = true, length = 60)
	private String lastName;
	@Column(nullable = false, length = 200)
	private String address;
	@Column(nullable = false, unique = true, length = 10)
	private Long contactNumber;
	@Column(nullable = false, unique = true, length = 60)
	private String emailId;
	@Column(nullable = false)
	private String password;
	
}
