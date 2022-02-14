package com.example.mp.generator.service.impl;

import com.example.mp.generator.entity.User;
import com.example.mp.generator.mapper.UserMapper;
import com.example.mp.generator.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yogurt
 * @since 2021-12-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
