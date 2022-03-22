package com.jupiterboy.springboot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jupiterboy.springboot.mybatisplus.entity.ChannelEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMapper extends BaseMapper<ChannelEntity> { }