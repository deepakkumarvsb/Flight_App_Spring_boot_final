package com.flightapp.UserService.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.UserService.service.UserService;
import com.flightapp.UserService.shared.UserDto;
import com.flightapp.UserService.spring.login.security.jwt.JwtUtils;
import com.flightapp.UserService.spring.login.security.services.UserDetailsImpl;
import com.flightapp.UserService.ui.MessageResponse;
import com.flightapp.UserService.ui.UserRequestModel;
import com.flightapp.UserService.ui.UserResponseModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;
  
  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  private ModelMapper modelMapper;
  
  @Autowired
  public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder encoder,
		JwtUtils jwtUtils, ModelMapper modelMapper) {
	super();
	this.authenticationManager = authenticationManager;
	this.userService = userService;
	this.encoder = encoder;
	this.jwtUtils = jwtUtils;
	this.modelMapper = modelMapper;
}

@PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody UserRequestModel userRequestModel) {

	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(userRequestModel.getEmailId(), userRequestModel.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
    
    UserResponseModel userResponseModel = modelMapper.map(userDetails, UserResponseModel.class);

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(userResponseModel);
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody UserRequestModel userRequestModel) {
	  
	modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	  
    if (userService.existsByEmailId(userRequestModel.getEmailId())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    UserResponseModel userResponseModel = userService.saveUser(modelMapper.map(userRequestModel, UserDto.class));

    return ResponseEntity.status(HttpStatus.CREATED).body(userResponseModel);
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}
