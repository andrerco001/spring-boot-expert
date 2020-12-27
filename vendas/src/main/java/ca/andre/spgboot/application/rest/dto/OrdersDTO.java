package ca.andre.spgboot.application.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrdersDTO {
	
	private Integer customer;
	private BigDecimal total;
	private List<ItenOrdersDTO> itens;
	

}
