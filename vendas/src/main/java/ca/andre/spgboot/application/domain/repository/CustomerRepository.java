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
public class CustomerRepository {
	
	private static String INSERT = "insert into customers (name) values (?) ";
	private static String SELECT_ALL = "select * from customers ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Customer save(Customer customer) {
		
		jdbcTemplate.update(INSERT, new Object[] {customer.getName()});
		
		return customer;
	}
	
	public List<Customer> findAll(){
		return jdbcTemplate.query(SELECT_ALL, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet resultSet, int i) throws SQLException{
				
				Integer id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				
				return new Customer(id, name);
			}
		});
	}
}
