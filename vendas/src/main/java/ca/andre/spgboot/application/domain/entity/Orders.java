package ca.andre.spgboot.application.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ca.andre.spgboot.application.domain.enums.StatusOrders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "customers_id")
	private Customer customer;
	
	@Column(name = "date_orders")
	private LocalDate orderDate;
	
	@Column(name = "total", precision = 20, scale = 2)
	private BigDecimal total;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private StatusOrders status;
	
	@OneToMany(mappedBy = "orders")
	private List<ItenOrders> itenOrders;

}
