package com.secure.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.secure.jwt.entity.AuthRequest;
import com.secure.jwt.service.CustomUserDetailsService;
import com.secure.jwt.util.JwtUtil;

@RestController
public class WelcomeController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to spring security session";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invaid username or Password");
		}
		//addition
		final UserDetails userDetails=userDetailsService.loadUserByUsername(authRequest.getUserName());
		final String token=jwtUtil.generateToken(userDetails);
		return token;
	//	return jwtUtil.generateToken(authRequest.getUserName());
	}

}
