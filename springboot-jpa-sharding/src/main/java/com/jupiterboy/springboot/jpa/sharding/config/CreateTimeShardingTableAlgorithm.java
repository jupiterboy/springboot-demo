package com.jupiterboy.springboot.jpa.sharding.config;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

public class CreateTimeShardingTableAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
        Date value = preciseShardingValue.getValue();
        String columnName = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        System.out.println("value:"+value);
        System.out.println("columnName:"+columnName);
        System.out.println("logicTableName:"+logicTableName);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String targetTable = preciseShardingValue.getLogicTableName() + "_" + sdf.format(value);
        System.out.println("targetTable:"+targetTable);
        if (collection.contains(targetTable)) {
            return targetTable;
        }
        throw new UnsupportedOperationException("无效的表名称: " + targetTable);
    }
}
