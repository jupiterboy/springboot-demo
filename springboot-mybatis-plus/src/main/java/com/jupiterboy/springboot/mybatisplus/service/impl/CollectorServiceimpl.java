package com.jupiterboy.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jupiterboy.springboot.mybatisplus.entity.CollectorEntity;
import com.jupiterboy.springboot.mybatisplus.mapper.CollectorMapper;
import com.jupiterboy.springboot.mybatisplus.service.CollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CollectorServiceimpl extends ServiceImpl<CollectorMapper, CollectorEntity> implements CollectorService {

    @Autowired
    private CollectorMapper collectorMapper;

    @Override
    public List<CollectorEntity> findAll() {
        return collectorMapper.selectList(null);
    }
}