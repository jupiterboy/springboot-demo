package com.jupiterboy.springboot.controller;

import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jupiterboy.springboot.Customer;
import com.jupiterboy.springboot.CustomerService;

@RestController
public class CustomerController {
	
	private static Random random = new Random();

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customer/add/{id}")
	public Customer addCustomer(@PathVariable String id) {
		String name = "name-"+ random.nextInt();
		int age = random.nextInt();
		String address = "address-" + random.nextInt();
		return customerService.saveCustomer(id, name, age, address);
	}
	
	@GetMapping("/customer/get/{id}")
    public Customer getCustomer(@PathVariable String id) {

		return customerService.getCustomer(id);
    }
    
    @RequestMapping("/customer/delete/{id}")
    public Customer deleteCustomer(@PathVariable String id) {
    	return customerService.deleteCustomer(id);
    }
    
    @RequestMapping("/customer/update/{id}")
    public Customer updateCustomer(@PathVariable String id) {
    	String address = "address-" + random.nextInt();
    	return customerService.updateCustomer(id, address);
    }
    
    @RequestMapping("/customer/customers")
    public Collection<Customer> getCustomers() {
    	return customerService.getCustomers();
    }
}
