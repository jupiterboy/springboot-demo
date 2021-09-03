package com.jupiterboy.springboot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

// reference https://www.cnblogs.com/fashflying/p/6908028.html
@Service
@CacheConfig(cacheNames = "customer")
public class CustomerService {
	
	private Map<String, Customer> maps = new HashMap<String, Customer>();
	
	@Cacheable(value = "customer", key = "'customer' + #id" )
	public Customer getCustomer(String id) {
		System.out.println("---------------------getCustomer");
		return maps.get(id);
	}

	@CachePut(value = "customer", key = "'customer' + #id")
	@CacheEvict(value="customers", allEntries=true)
	public Customer saveCustomer(String id, String name, int age, String address) {
		System.out.println("---------------------saveCustomer");
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(name);
		customer.setAddress(address);
		customer.setAge(age);
		maps.put(id, customer);
		return customer;
	}
	
	@CachePut(value="customer", key="'customer' + #id")
	@CacheEvict(value="customers", allEntries=true)
	public Customer updateCustomer(String id, String address) {
		System.out.println("---------------------updateCustomer");
		maps.get(id).setAddress(address);
		return maps.get(id);
	}
	
	@CacheEvict(value = "customers", allEntries=true ,key = "'customer' + #id")
	public Customer deleteCustomer(String id) {
		System.out.println("---------------------deleteCustomer");
		return maps.remove(id);
	}
	
	@Cacheable(value="customers")
	public Collection<Customer> getCustomers() {
		System.out.println("---------------------getCustomers");
		return maps.values();
	}

	
}
