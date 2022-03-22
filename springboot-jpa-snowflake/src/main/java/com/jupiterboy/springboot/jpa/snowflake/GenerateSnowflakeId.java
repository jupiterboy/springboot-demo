package com.jupiterboy.springboot.jpa.snowflake;

import cn.hutool.core.lang.Snowflake;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.util.Properties;

public class GenerateSnowflakeId implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return SpringUtils.getBean(Snowflake.class).nextId();
    }

}

//public class GenerateSnowflakeId implements IdentifierGenerator, Configurable {
//
//    @Override
//    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
//        return SpringUtils.getBean(Snowflake.class).nextId();
//    }
//
//    @Override
//    public void configure(org.hibernate.type.Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
//
//    }
//}