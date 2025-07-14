package com.fis.bankapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fis.bankapp.model.Accounts;
import com.fis.bankapp.model.Customer;
import com.fis.bankapp.service.AccountService;
import com.fis.bankapp.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class BankAppController {
	@Autowired
	private CustomerService customerService;

	@Autowired
	private AccountController accountController;

	@Autowired
	private AccountService accountService;

	@PostMapping("/create")
	public ResponseEntity createCustomer(@RequestBody Customer customer) {
		Customer createCustomer = customerService.createCustomer(customer);
		accountController.createAccount(customer.getAcctID(), 0, "Active");

		return ResponseEntity.status(HttpStatus.CREATED).body(createCustomer);
	}

	@GetMapping("/customer/{acctId}")
	public ResponseEntity<Customer> getCustomerInfo(@PathVariable("acctId") int acctId) {
		Optional<Customer> customer = customerService.getCustomerInfo(acctId);

		return customer.map(ResponseEntity::ok) // If present → 200 OK with body
				.orElseGet(() -> ResponseEntity.notFound().build()); // If empty → 404 Not Found
	}

	@GetMapping("/account/{acctID}")
	public ResponseEntity<Accounts> getAccountInfo(@PathVariable int acctID) {
		Accounts accounts = accountService.getAccountInfo(acctID);

		if (accounts != null) {
			return ResponseEntity.ok(accounts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
