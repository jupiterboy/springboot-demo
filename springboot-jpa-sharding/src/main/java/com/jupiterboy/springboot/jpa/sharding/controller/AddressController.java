package com.jupiterboy.springboot.jpa.sharding.controller;

import com.jupiterboy.springboot.jpa.sharding.entity.Address;
import com.jupiterboy.springboot.jpa.sharding.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping("/address/save")
    @ResponseBody
    public String save() {
        for (int i = 0; i <10 ; i++) {
            Address address=new Address();
            address.setCode("code_"+i);
            address.setName("name_"+i);
            address.setPid(i+"");
            address.setType(0);
            address.setLit(i%2==0?1:2);
            addressRepository.save(address);
        }

        return "success";
    }

    @RequestMapping("/address/get/{id}")
    @ResponseBody
    public Address get(@PathVariable Long id) {
        return addressRepository.getOne(id);
    }
}
