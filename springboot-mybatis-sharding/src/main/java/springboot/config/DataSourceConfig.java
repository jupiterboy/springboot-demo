package springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {


//    @Bean
//    @Primary
//    public DataSource shardingDataSource() throws SQLException {
//
//        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
//
//        //用户表配置，可以添加多个配置
//        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
//        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration1());
//        shardingRuleConfig.getBindingTableGroups().add("gps");
//
//        //设置数据库策略，传入的是sys_time
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("sys_time", DatabaseShardingAlgorithm.class.getName()));
//        //设置数据表策略，传入的是sys_time
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("sys_time", TableShardingAlgorithm.class.getName()));
//
//
//        return new ShardingDataSource(shardingRuleConfig.build(createDataSourceMap()));
//        return ShardingDataSourceFactory.createDataSource()
//    }
//
//    private Map<String, DataSource> createDataSourceMap() {
//        Map<String, DataSource> result = new HashMap<>();
//        result.put("order1", createDataSource("jdbc:mysql://localhost:3306/order1?characterEncoding=utf8&useSSL=false"));
//        result.put("order2", createDataSource("jdbc:mysql://localhost:3306/order2?characterEncoding=utf8&useSSL=false"));
//        return result;
//    }
//
//
//    private DataSource createDataSource(final String dataSourceName) {
//        //使用默认连接池
//        DruidDataSource result = new DruidDataSource();
//        result.setDriverClassName(com.mysql.jdbc.Driver.class.getName());
//        //设置数据库路径
//        result.setUrl(dataSourceName);
//        //设置数据库用户名
//        result.setUsername("root");
//        //设置数据密码
//        result.setPassword("123456");
//        return result;
//    }
//
//    @Bean
//    public DataSourceTransactionManager transactitonManager(DataSource shardingDataSource) {
//        return new DataSourceTransactionManager(shardingDataSource);
//    }
//
//
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource shardingDataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(shardingDataSource);
//        return sessionFactory.getObject();
//    }
}
