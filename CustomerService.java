package com.fis.bankapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fis.bankapp.dao.CustomerRepository;
import com.fis.bankapp.model.Customer;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	public Customer createCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Optional<Customer> getCustomerInfo(int acctID) {
		return customerRepository.findById(acctID);
	}

	public void deleteCustomer(int acctID) {
		customerRepository.deleteById(acctID);
	}

}
