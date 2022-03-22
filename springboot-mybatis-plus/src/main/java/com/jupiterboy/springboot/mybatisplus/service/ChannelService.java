package com.jupiterboy.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jupiterboy.springboot.mybatisplus.entity.ChannelEntity;

import java.util.List;

public interface ChannelService extends IService<ChannelEntity> {
    List<ChannelEntity> findAll();
}
