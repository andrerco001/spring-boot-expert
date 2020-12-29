package ca.andre.spgboot.application.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import ca.andre.spgboot.application.service.impl.UserServiceImpl;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
	private UserServiceImpl userServiceImpl;
	
	public JwtAuthFilter(JwtService jwtService, UserServiceImpl userServiceImpl) {
		this.jwtService = jwtService;
		this.userServiceImpl = userServiceImpl;
	}

	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String authorization = request.getHeader("Authorization");
		
		if(authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.split(" ")[1];
			boolean isValid =  jwtService.tokenValid(token);
			if(isValid) {
				String usernameUser = jwtService.getUsernameUser(token);
				UserDetails user = userServiceImpl.loadUserByUsername(usernameUser);
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new 
						UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
