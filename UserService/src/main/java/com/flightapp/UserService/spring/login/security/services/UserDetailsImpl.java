package com.flightapp.UserService.spring.login.security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightapp.UserService.model.UserEntity;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private long userId;
	private String firstName;
	private String lastName;
	private String address;
	private Long contactNumber;
	private String emailId;
	private String userName;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(long userId, String firstName, String lastName, String address, Long contactNumber,
			String emailId, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UserEntity user) {

		List<GrantedAuthority> authorities = new ArrayList<>();

		return new UserDetailsImpl(user.getUserId(), user.getFirstName(), user.getLastName(), user.getAddress(),
				user.getContactNumber(), user.getEmailId(), user.getPassword(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public long getUserId() {
		return userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

//	public String getPassword() {
//		return password;
//	}
//	
//	public String getUserName() {
//		return userName;
//	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(address, other.address) && Objects.equals(authorities, other.authorities)
				&& Objects.equals(contactNumber, other.contactNumber) && Objects.equals(emailId, other.emailId)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(password, other.password) && userId == other.userId;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return emailId;
	}

}
