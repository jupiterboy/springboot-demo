package com.tcb.common.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;

/**
 * @author yuhai
 *
 * 记录检测项的监测数据
 */
@Setter
@Getter
@Entity(name = "monitor_value")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class MonitorValueEntity extends ValueEntity {

    /**
     * 设备编号
     */
    private String deviceNumber;

    /**
     * 测点编号
     */
    private String measurePointNumber;

    /**
     * 监测项类型
     */
    private Integer monitorItemType;

    /**
     * 监测值
     */
    private Double value;

    /**
     * 监测状态
     */
    private Integer status;

    /**
     * 原始的测量数据id集合
     */
    private Long measureValueId;

}

