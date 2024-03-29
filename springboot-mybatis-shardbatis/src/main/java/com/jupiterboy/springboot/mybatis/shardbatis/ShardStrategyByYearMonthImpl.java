package com.jupiterboy.springboot.mybatis.shardbatis;

import com.google.code.shardbatis.strategy.ShardStrategy;
import com.jupiterboy.springboot.mybatis.shardbatis.entity.MyUser;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 分表策略，自动按当前年月分表
 * @author yehx
 *
 */
@Slf4j
public class ShardStrategyByYearMonthImpl implements ShardStrategy {

//    private static Log log = LogFactory.getLog(ShardStrategyByYearMonthImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
    /**
     * 得到实际表名
     * @param baseTableName 逻辑表名,一般是没有前缀或者是后缀的表名
     * @param params        mybatis执行某个statement时使用的参数
     * @param mapperId      mybatis配置的statement id
     * @return
     */

    public String getTargetTableName(String baseTableName,Object params, String mapperId) {
        return baseTableName +"_" + sdf.format(new Date());
    }
}