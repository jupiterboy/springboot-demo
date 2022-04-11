package com.tcb.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.model.parameter.MonitorItemParameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuhai
 *
 * 监测项
 *
 * 测点下具体监控指标
 */
@Setter
@Getter
@Entity(name = "monitor_item")
public class MonitorItemEntity extends ConfigEntity<MonitorItemParameter> {

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "measure_point_id")
    private MeasurePointEntity measurePointEntity;

    // 最近一次告警记录的ID
    private Long alarmId;

    // 最新状态
    private Integer status = AlarmStatus.NORMAL;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date alarmTime;

    /**
     * 记录监测项连续告警次数
     */
    private int count;

}
