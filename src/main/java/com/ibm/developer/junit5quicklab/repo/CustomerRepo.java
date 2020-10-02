package com.ibm.developer.junit5quicklab.repo;

import org.springframework.data.repository.CrudRepository;

import com.ibm.developer.junit5quicklab.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
	Iterable<Customer> findCustomersByFirstNameAndLastName(String firstName, String lastName);
}
