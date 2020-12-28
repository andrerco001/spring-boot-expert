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
	
	@NotNull(message = "{field.code-customer.required}")
	private Integer customer;
	
	@NotNull(message = "{field.total-orders.required}")
	private BigDecimal total;
	
	@NotEmptyList(message = "{field.itens-orders.required}")
	private List<ItenOrdersDTO> itens;

}
