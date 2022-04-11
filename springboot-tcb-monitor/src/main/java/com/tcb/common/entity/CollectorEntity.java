package com.tcb.common.entity;

import com.tcb.common.model.parameter.CollectorParameter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * 采集器
 */
@Setter
@Getter
@Entity(name = "collector")
public class CollectorEntity extends ConfigEntity<CollectorParameter> {

    /**
     * 采集所属组织机构
     */
    private String orgId;

    /**
     * 采集器包含的采集通道
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "collectorEntity")
    protected Set<ChannelEntity> channelEntities = new HashSet<ChannelEntity>();

    /**
     * 采集器包含的采集模块
     * 采集模块是对采集通道的分组
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "collectorEntity")
    protected Set<ModuleEntity> moduleEntities = new HashSet<ModuleEntity>();

    public void addChannelEntity(ChannelEntity channelEntity) {
        channelEntity.setCollectorEntity(this);
        channelEntities.add(channelEntity);
    }

    public void addModuleEntity(ModuleEntity moduleEntity) {
        moduleEntity.setCollectorEntity(this);
        moduleEntities.add(moduleEntity);
    }
}