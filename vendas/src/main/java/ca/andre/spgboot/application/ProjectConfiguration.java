package ca.andre.spgboot.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ProjectConfiguration
{
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("RUN DEVLOPMENT'S CONFIGURATION");
			
		};
	}

}
