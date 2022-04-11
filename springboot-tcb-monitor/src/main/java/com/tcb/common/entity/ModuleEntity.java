package com.tcb.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.model.parameter.ModuleParameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 采集器模组
 */
@Setter
@Getter
@Entity(name = "module")
public class ModuleEntity extends ConfigEntity<ModuleParameter> {

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "collector_id")
    private CollectorEntity collectorEntity;

    /**
     * 所属模块的采集通道
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "moduleEntity")
    private Set<ChannelEntity> channelEntities = new HashSet<ChannelEntity>();

    public void addChannelEntity(ChannelEntity channelEntity) {
        channelEntity.setModuleEntity(this);
    	channelEntities.add(channelEntity);
    }
}
