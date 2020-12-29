package ca.andre.spgboot.application.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.andre.spgboot.application.domain.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		ca.andre.spgboot.application.domain.entity.User user = userRepository
		.findByUsername(username)
		.orElseThrow(() -> new UsernameNotFoundException("User not found in the database!"));
		
		String[] roles = user.isAdmin() ? new String[] {"ADMIN","USER"} : new String[] {"USER"};
		
		return User
				.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(roles)
				.build(); 
	}

	public ca.andre.spgboot.application.domain.entity.User save(ca.andre.spgboot.application.domain.entity.User user) {
		
		return userRepository.save(user);
	}

}
