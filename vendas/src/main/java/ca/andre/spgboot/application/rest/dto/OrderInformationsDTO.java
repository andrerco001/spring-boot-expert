package ca.andre.spgboot.application.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderInformationsDTO {
	
	private Integer code;
	private String cpf;
	private String nameCustomer;
	private BigDecimal total;
	private String dateOrders;
	private String status;
	private List<ItenOrdersInformationDTO> itenOrdersInformationDTO;
	

}
