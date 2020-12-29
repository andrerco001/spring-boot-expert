package ca.andre.spgboot.application;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import ca.andre.spgboot.application.domain.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	@Value("${security.jwt.expiration}")
	private String expiration;
	
	@Value("${security.jwt.signature-key}")
	private String signatureKey;
	
	public String tokenGenerator(User user) {
		
		long expString = Long.valueOf(expiration);
		
		LocalDateTime dateTimeExpiration = LocalDateTime.now().plusMinutes(expString);
		
		Instant instant = dateTimeExpiration.atZone(ZoneId.systemDefault()).toInstant();

		Date date = Date.from(instant);
		
		return Jwts
				.builder()
				.setSubject(user.getUsername())
				.setExpiration(date)
				.signWith(SignatureAlgorithm.HS512, signatureKey)
				.compact();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SalesApplication.class);
		JwtService service = context.getBean(JwtService.class);
		User user = User.builder().username("user").build();
		String token = service.tokenGenerator(user);
		System.out.println(token);
		
	}
	
	

}
