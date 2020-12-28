package ca.andre.spgboot.application.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItenOrdersInformationDTO {
	
	private String productDescription;
	private BigDecimal unityPrice;
	private Integer quantity;
	
}
