package com.jupiterboy.springboot.jpa.sharding.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

public class UserShardingDBAlgorithm implements PreciseShardingAlgorithm<Integer> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
        Integer value = preciseShardingValue.getValue();
        String columnName = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        System.out.println("value:"+value);
        System.out.println("columnName:"+columnName);
        System.out.println("logicTableName:"+logicTableName);

        for (String each : collection) {
            System.out.println("------"+each);
            if(each.endsWith("" + value % 2)){
                return each;
            }
        }
        return null;
    }
}
