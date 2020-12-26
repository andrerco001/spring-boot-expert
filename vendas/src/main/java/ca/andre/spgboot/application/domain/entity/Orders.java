package ca.andre.spgboot.application.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	
	@OneToMany(mappedBy = "orders")
	private List<ItenOrders> itenOrders;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<ItenOrders> getItenOrders() {
		return itenOrders;
	}

	public void setItenOrders(List<ItenOrders> itenOrders) {
		this.itenOrders = itenOrders;
	}

	@Override
	public String toString() {
		return "Orders [ id = " + id + ", orderDate = " + orderDate + ", total = " + total + " ]";
	}

}
