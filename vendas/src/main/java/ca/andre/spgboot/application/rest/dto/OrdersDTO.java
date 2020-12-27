package ca.andre.spgboot.application.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
	
	private Integer customer;
	private BigDecimal total;
	private List<ItenOrdersDTO> itens;
	

}
