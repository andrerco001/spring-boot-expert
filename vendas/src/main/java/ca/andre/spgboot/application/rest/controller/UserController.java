package ca.andre.spgboot.application.rest.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ca.andre.spgboot.application.domain.entity.User;
import ca.andre.spgboot.application.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
	
	private final UserServiceImpl userServiceImpl;
	
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User save(@RequestBody @Valid User user) {
		String passwordCrypt = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordCrypt);
		return userServiceImpl.save(user);
	}

}
