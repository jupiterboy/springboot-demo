package com.jupiterboy.springboot.jpa.sharding.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import com.mysql.cj.jdbc.Driver;

import javax.sql.DataSource;

/**
 * @author Hyacinth
 * @date 2021/6/17
 */
@Component
public class AutoGenerateTableTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoGenerateTableTask.class);

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/jpaorder1?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=true\n";
    private String userName = "root";
    private String password ="123456";

    @Autowired
    private DataSource dataSource;

    // 每月25日自动创建下月数据表
    @Scheduled(cron = "0 0 0 25 * ?")
    public void run() {
        Connection conn = null;
        Statement stat = null;
        try {
            //连接数据库
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, userName, password);
            conn = dataSource.getConnection();
            stat = conn.createStatement();
            //获取数据库表名
            String tableName = "t_user_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
            ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null);
            // 判断表是否存在，如果存在则什么都不做，否则创建表
            if (rs.next()) {
                System.out.println("数据库已存在");
            } else {
                //创建行政区划表
                String createSql = String.format("CREATE TABLE %s  (\n" +
                        "  `id` bigint(0) NOT NULL AUTO_INCREMENT,\n" +
                        "  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',\n" +
                        "  `city_id` int(0) NULL DEFAULT NULL COMMENT '城市',\n" +
                        "  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别',\n" +
                        "  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',\n" +
                        "  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',\n" +
                        "  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',\n" +
                        "  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',\n" +
                        "  PRIMARY KEY (`id`) USING BTREE\n" +
                        ") ENGINE = InnoDB AUTO_INCREMENT = 697499952036708352 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;", tableName);
                stat.executeUpdate(createSql);
            }
        } catch (Exception e) {
            LOGGER.error("创建表出错,message-{}", e.getMessage());
        } finally {
            // 释放资源
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取下个月的日期
     *
     * @return yyyyMM
     */
    public String getPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        return simpleDateFormat.format(calendar.getTime());
    }

}