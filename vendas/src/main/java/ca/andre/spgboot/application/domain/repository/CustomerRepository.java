package ca.andre.spgboot.application.domain.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ca.andre.spgboot.application.domain.entity.Customer;

@Repository
public class CustomerRepository 
{
	private static String INSERT = "insert into customers (name) values (?) ";
	private static String SELECT_ALL = "select * from customers ";
	private static String UPDATE = "update customers set name = ? where id = ? ";
	private static String DELETE = "delete from customers where id = ? ";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Create
	public Customer save(Customer customer) 
	{
		jdbcTemplate.update(INSERT, new Object[] { customer.getName() });

		return customer;
	}
	
	// find Customers Mapper
	private RowMapper<Customer> findCustomersMapper() {
		return new RowMapper<Customer>() 
		{
			@Override
			public Customer mapRow(ResultSet resultSet, int i) throws SQLException 
			{
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");

				return new Customer(id, name);
			}
		};
	}

	// Read - find all
	public List<Customer> findAll() 
	{
		return jdbcTemplate.query(SELECT_ALL, findCustomersMapper());
	}
		
	// Read - find by name
	public List<Customer> findByName(String name){
		
		return jdbcTemplate.query(
				SELECT_ALL.concat(" where name like ? "),
				new Object[] {"%" + name + "%"},
				findCustomersMapper());
	}

	// Update
	public Customer update(Customer customer) 
	{
		jdbcTemplate.update(UPDATE, new Object[] { customer.getName(), customer.getId() });

		return customer;
	}

	// Delete
	public void delete(Integer id) 
	{
		jdbcTemplate.update(DELETE, new Object[] { id });
	}

}
