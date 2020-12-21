package ca.andre.spgboot.application.domain.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.andre.spgboot.application.domain.entity.Customer;

@Repository
public class CustomerRepository 
{
	@Autowired
	private EntityManager entityManager;

	// Create
	@Transactional
	public Customer save(Customer customer) 
	{
		entityManager.persist(customer);
		return customer;
	}

	// Read - find all
	@Transactional(readOnly = true)
	public List<Customer> findAll() 
	{
		return entityManager.createQuery("from Customer", Customer.class).getResultList();
	}
		
	// Read - find by name
	@Transactional(readOnly = true)
	public List<Customer> findByName(String name)
	{
		String jpql = "select c from Customer c where c.name like :name ";
		TypedQuery<Customer> query = entityManager.createQuery(jpql, Customer.class);
		query.setParameter("name", "%" + name + "%");
		return query.getResultList();
	}

	// Update
	@Transactional
	public Customer update(Customer customer) 
	{
		entityManager.merge(customer);
		return customer;
	}

	// Delete by customer
	@Transactional
	public void deleteByCustomer(Customer customer) {
		
		if(!entityManager.contains(customer)) {
			entityManager.merge(customer);
		}
		
		entityManager.remove(customer);
	}
	
	// Delete by id
	@Transactional
	public void delete(Integer id) 
	{
		Customer customer = entityManager.find(Customer.class, id);
		deleteByCustomer(customer);
	}

}
