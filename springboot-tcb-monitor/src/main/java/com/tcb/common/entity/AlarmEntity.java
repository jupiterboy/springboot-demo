package com.tcb.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcb.common.model.parameter.MonitorItemParameter;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author yuhai
 *
 * 告警数据的实体类
 */
@Setter
@Getter
@Entity(name = "alarm")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class AlarmEntity extends UpdateEntity<Long> {

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "snowflakeId", strategy = "com.tcb.common.utils.SnowflakeIdentifierGenerator" )
    @GeneratedValue(generator = "snowflakeId")
    private Long id;

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
     * 告警描述
     */
    private String message;

    /**
     * 连续报警发生的次数
     */
    private Integer count = 1;

    /**
     * 发生报警时监测项的参数
     */
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private MonitorItemParameter parameter;

    /**
     * 持续时间
     */
    private Integer duration;

    /**
     * 取消时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;
}
