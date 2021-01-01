package ca.andre.spgboot.application.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer 
{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name", length = 100)
	@NotEmpty(message = "{field.name.required}")
	private String name;
	
	@Column(name = "cpf", length = 11)
	@NotEmpty(message = "{field.cpf.required}")
	@CPF(message = "{field.cpf.invalid}")
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
	private Set<Orders> orders;
	
	public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Customer(String name) {
		this.name = name;
	}
	
}
