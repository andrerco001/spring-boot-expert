package ca.andre.spgboot.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket docket(){
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.select()
				.apis(RequestHandlerSelectors.basePackage("ca.andre.spgboot.application.rest.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
				.title("Sales Application API")
				.description("Sales Project API")
				.version("1.0")
				.contact(contact())
				.build();
	}
	
	private springfox.documentation.service.Contact contact() {
		return new Contact("André Oliveira"
				, "https://github.com/andrerco001/spring-boot-expert"
				, "andrerco002@gmail.com");
	}
	

}
