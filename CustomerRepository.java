package com.fis.bankapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.fis.bankapp.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
