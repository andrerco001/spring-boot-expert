package ca.andre.spgboot.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfiguration
{
    @Bean(name = "applicationName")
    public String applicationName()
    {
        return "Sales system";
    }
}
