package ca.andre.spgboot.application.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import ca.andre.spgboot.application.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO {
	
	@NotNull(message = "Enter the customer code.")
	private Integer customer;
	
	@NotNull(message = "The total field is required.")
	private BigDecimal total;
	
	@NotEmptyList(message = "The order can be placed without items.")
	private List<ItenOrdersDTO> itens;
	

}
