package com.ibm.developer.junit5quicklab.service;

import java.util.List;

import com.ibm.developer.junit5quicklab.model.Customer;

public interface CustomerService {
	Customer findCustomerById(long id);

	List<Customer> findAllCustomers();
	
	Customer saveCustomer(Customer customer);

	boolean deleteResource(Long id);

	List<Customer> findByCustomerFirstNameLastName(String fName, String lName);
}
