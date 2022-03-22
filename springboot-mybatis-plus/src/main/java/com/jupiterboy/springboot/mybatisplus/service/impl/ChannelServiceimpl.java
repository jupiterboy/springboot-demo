package com.jupiterboy.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jupiterboy.springboot.mybatisplus.entity.ChannelEntity;
import com.jupiterboy.springboot.mybatisplus.entity.User;
import com.jupiterboy.springboot.mybatisplus.mapper.ChannelMapper;
import com.jupiterboy.springboot.mybatisplus.mapper.UserMapper;
import com.jupiterboy.springboot.mybatisplus.service.ChannelService;
import com.jupiterboy.springboot.mybatisplus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelServiceimpl extends ServiceImpl<ChannelMapper, ChannelEntity> implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public List<ChannelEntity> findAll() {
        return channelMapper.selectList(null);
    }
}