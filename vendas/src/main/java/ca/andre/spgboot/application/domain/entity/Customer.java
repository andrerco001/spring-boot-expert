package ca.andre.spgboot.application.domain.entity;

public class Customer {

	private Integer id;
	private String name;
	
	public Customer() {
	
	}
	
	public Customer(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Customer(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [ Id = " + id + ", Name = " + name + " ]";
	}
	
}
