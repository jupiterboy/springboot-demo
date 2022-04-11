package com.tcb.common.service.impl;

import com.tcb.common.model.manager.CollectorManager;
import com.tcb.common.model.manager.DeviceManager;
import com.tcb.common.service.BaseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Getter
@Setter
public class BaseServiceImpl<R extends JpaRepository<E, ID>, E, ID> implements BaseService<E, ID> {

    @Autowired
    protected CollectorManager collectorManager;

    @Autowired
    protected DeviceManager deviceManager;

    @Autowired
    protected R repository;

    @Override
    public E saveEntity(E e) {
        try{
            return repository.save(e);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteEntity(ID id) {
        try{
            repository.deleteById(id);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateEntity(E e) {
        try{
            repository.save(e);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public E getEntity(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<E> findAllEntities() {
        return repository.findAll();
    }
}
