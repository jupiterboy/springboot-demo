package com.jupiterboy.springboot.mybatis.shardbatis;

import com.google.code.shardbatis.strategy.ShardStrategy;
import com.jupiterboy.springboot.mybatis.shardbatis.entity.MyUser;
import lombok.extern.slf4j.Slf4j;

/**
 * 分表策略，自动按当前年月分表
 * @author yehx
 *
 */
@Slf4j
public class ShardStrategyByUserName implements ShardStrategy {

    public String getTargetTableName(String baseTableName,Object params, String mapperId) {
        System.out.println(params.getClass().getName());
        System.out.println(MyUser.class.getName());
        System.out.println(params.getClass().hashCode());
        System.out.println(MyUser.class.hashCode());
        System.out.println(params instanceof MyUser);
        if(params != null){
            MyUser myUser = (MyUser)params;
            if(myUser.getUsername().endsWith("1")){
                return baseTableName +"_1";
            }
            if(myUser.getUsername().endsWith("2")){
                return baseTableName +"_2";
            }
        }

        return baseTableName;
    }
}