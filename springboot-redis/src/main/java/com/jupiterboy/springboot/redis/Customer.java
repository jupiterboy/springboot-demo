package com.jupiterboy.springboot.redis;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Customer {

	private String id;
	private String name;
	private int age;
	private String address;
}
