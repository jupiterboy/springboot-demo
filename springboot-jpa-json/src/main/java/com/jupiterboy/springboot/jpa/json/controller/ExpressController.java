package com.jupiterboy.springboot.jpa.json.controller;

import com.jupiterboy.springboot.jpa.json.model.AddedServiceModel;
import com.jupiterboy.springboot.jpa.json.model.CargoModel;
import com.jupiterboy.springboot.jpa.json.entity.ExpressOrder;
import com.jupiterboy.springboot.jpa.json.repo.ExpressOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class ExpressController {

    @Autowired
    private ExpressOrderRepository expressOrderRepository;

    @Autowired
    private EntityManager em;

    @GetMapping("/express/save")
    public String save(){

        CargoModel cargoModel = new CargoModel();
        cargoModel.setName(String.valueOf(System.currentTimeMillis()));
        cargoModel.setCount(String.valueOf(new Random().nextInt(10)));
//        cargoModel.setPrice("12.3");
        cargoModel.setRemark("remark");

        List<AddedServiceModel> addedServiceModelList = new ArrayList<>();
        for(int i=0;i<5;i++){
            AddedServiceModel model = new AddedServiceModel();
            model.setName("name"+i);
            model.setValue(String.valueOf(i));
            addedServiceModelList.add(model);
        }
        ExpressOrder order = new ExpressOrder();
        order.setCargoModel(cargoModel);
        order.setAddedServiceModelList(addedServiceModelList);
        expressOrderRepository.save(order);
        return "Success";
    }

    @GetMapping("/express/get/{id}")
    public ExpressOrder get(@PathVariable Long id){
        return expressOrderRepository.findById(id).orElse(null);
    }


    @GetMapping("/express/getByName/{cargoName}")
    public List<ExpressOrder> get(@PathVariable String cargoName){
        return expressOrderRepository.findByCargoName(cargoName);
    }
}
