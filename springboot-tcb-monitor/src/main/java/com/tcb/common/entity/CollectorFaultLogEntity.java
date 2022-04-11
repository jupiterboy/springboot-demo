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
@Entity(name = "collector_fault_log")
public class CollectorFaultLogEntity extends UpdateEntity<String> {

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
     * 模组类型
     */
    private Integer moduleType;

    /**
     * 通道编号
     */
    private String channelNumber;

    /**
     * 故障类型
     */
    private Integer type;

    /**
     * 故障状态，0：正常，1：故障
     */
    private Integer status;

    /**
     * 故障持续时间
     */
    private Integer duration;

}
