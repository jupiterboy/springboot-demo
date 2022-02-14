package com.jupiterboy.springboot.mybatis.shardbatis;

import com.google.code.shardbatis.plugin.ShardPlugin;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class MyShardPlugin extends ShardPlugin {
}
