package com.jupiterboy.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jupiterboy.springboot.mybatisplus.entity.CollectorEntity;

import java.util.List;

public interface CollectorService extends IService<CollectorEntity> {
    List<CollectorEntity> findAll();
}
