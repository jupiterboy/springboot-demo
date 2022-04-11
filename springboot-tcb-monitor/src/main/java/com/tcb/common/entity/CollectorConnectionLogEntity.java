package com.tcb.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author yuhai
 *
 * 采集器上下日志
 */
@Setter
@Getter
@Entity(name = "collector_connection_log")
public class CollectorConnectionLogEntity extends UpdateEntity<String> {

    /**
     * 主键ID
     */
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /**
     * 采集器编号
     */
    private String collectorNumber;

    /**
     * 采集器上下线标识，0:下线，1:上线
     */
    private Integer status;

    /**
     * 上线持续时间，单位：秒
     */
    private Long duration;

}
