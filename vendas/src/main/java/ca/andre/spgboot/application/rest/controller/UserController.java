package ca.andre.spgboot.application.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ca.andre.spgboot.application.domain.entity.User;
import ca.andre.spgboot.application.exception.PasswordInvalidException;
import ca.andre.spgboot.application.rest.dto.CredentialsDTO;
import ca.andre.spgboot.application.rest.dto.TokenDTO;
import ca.andre.spgboot.application.security.jwt.JwtService;
import ca.andre.spgboot.application.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody @Valid User user) {
		String passwordCrypt = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordCrypt);
		return userServiceImpl.save(user);
	}
	
	@PostMapping("/auth")
	public TokenDTO authenticate(@RequestBody CredentialsDTO credentialsDTO) {
		try {
			User user = User.builder()
					.username(credentialsDTO.getUsername())
					.password(credentialsDTO.getPassword()).build();
			
			UserDetails userAuthenticated = userServiceImpl.authenticate(user);
			String token = jwtService.tokenGenerator(user);
			return new TokenDTO(userAuthenticated.getUsername(), token);
		
		} catch (UsernameNotFoundException | PasswordInvalidException e) {
			
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,e.getMessage());
		} 
		
	}

}
