package ca.andre.spgboot.application.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ca.andre.spgboot.application.domain.repository.UserRepository;
import ca.andre.spgboot.application.exception.PasswordInvalidException;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public ca.andre.spgboot.application.domain.entity.User save(ca.andre.spgboot.application.domain.entity.User user) {

		return userRepository.save(user);
	}
	
	public UserDetails authenticate(ca.andre.spgboot.application.domain.entity.User user) {
		UserDetails userDetails = loadUserByUsername(user.getUsername());
		boolean isPasswordEquals = encoder.matches(user.getPassword(), userDetails.getPassword());
		
		if(isPasswordEquals) {
			return userDetails;
		}
		
		throw new PasswordInvalidException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ca.andre.spgboot.application.domain.entity.User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found in the database!"));

		String[] roles = user.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

		return User.builder().username(user.getUsername()).password(user.getPassword()).roles(roles).build();
	}

}
