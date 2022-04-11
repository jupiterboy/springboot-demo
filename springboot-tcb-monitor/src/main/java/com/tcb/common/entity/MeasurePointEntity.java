package com.tcb.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.model.parameter.MeasurePointParameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yuhai
 *
 * 测点实体类
 *
 * 测点为传感器实际安装插入的物理位置
 */
@Setter
@Getter
@Entity(name = "measure_point")
public class MeasurePointEntity extends ConfigEntity<MeasurePointParameter> {

    @JsonIgnore
    @OneToOne(mappedBy = "measurePointEntity", fetch = FetchType.EAGER)
    private ChannelEntity channelEntity;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "component_id")
    private ComponentEntity componentEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "measurePointEntity")
    protected Set<MonitorItemEntity> monitorItemEntities = new HashSet<MonitorItemEntity>();

    public void addMonitorItemEntity(MonitorItemEntity monitorItemEntity) {
        monitorItemEntity.setMeasurePointEntity(this);
        this.monitorItemEntities.add(monitorItemEntity);
    }
}
