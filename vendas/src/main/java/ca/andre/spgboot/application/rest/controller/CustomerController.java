package ca.andre.spgboot.application.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/customer")
public class CustomerController 
{
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	@ResponseBody
	public String helloCustomer(@PathVariable("name") String nameCustomer) {
		return String.format("Hello %s", nameCustomer);
	}
	
}
