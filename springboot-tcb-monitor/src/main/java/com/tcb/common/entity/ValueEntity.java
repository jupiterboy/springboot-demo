package com.tcb.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author yuhai
 * 公共的实体类
 *
 * ValueEntity统一使用雪花算法生成id
 */
@Setter
@Getter
@MappedSuperclass
public class ValueEntity extends BaseEntity<Long>{

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "snowflakeId", strategy = "com.tcb.common.utils.SnowflakeIdentifierGenerator" )
    @GeneratedValue(generator = "snowflakeId")
    private Long id;

}
