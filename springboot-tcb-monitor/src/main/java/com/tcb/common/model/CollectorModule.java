package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.ChannelStatus;
import com.tcb.common.entity.ChannelEntity;
import com.tcb.common.entity.ModuleEntity;
import com.tcb.common.model.manager.ChannelManager;
import com.tcb.common.model.parameter.ModuleParameter;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuhai
 *
 * 采集器模块，映射采集器下的具体模块
 *
 */
@Getter
@Setter
public class CollectorModule extends ChannelManager<ModuleEntity, ModuleParameter> {

    /**
     * 模块所属采集器
     */
    @JsonIgnore
    private Collector collector;

    public CollectorModule(Collector collector, ModuleEntity entity) {
        super(entity);
        this.collector = collector;

        // 从缓存初始化时，entity直接没有自动进行关联，需要手动关联
        entity.setCollectorEntity(collector.getEntity());
    }

    /**
     * 更新采集器模组
     * @param entity 模组实体
     */
    @Override
    public void update(ModuleEntity entity) {
        super.update(entity);
        removeInvalidChannels(entity);
        updateChannels(entity);
    }
    
    // 更新模组下的通道
    private void updateChannels(ModuleEntity entity) {
        entity.getChannelEntities().forEach(channelEntity -> {
            Channel channel = getChannel(channelEntity.getId());
            // 如果没有，从采集器里找到通道添加到模组中，模组自身不创建通道
            if (channel == null) {
                channel = collector.getChannel(channelEntity.getId());
                channel.setCollectorModule(this);
                addChannel(channel);
            }
        });
    }

    // 删除无效的通道
    private void removeInvalidChannels(ModuleEntity entity) {
        Set<String> channelIds = entity.getChannelEntities().stream().map(ChannelEntity::getId).collect(Collectors.toSet());
        Set<Channel> unusedChannels = getChannels().stream().filter(channel -> !channelIds.contains(channel.getId())).collect(Collectors.toSet());
        getChannels().removeAll(unusedChannels);
    }

    /**
     * 获取模组工作状态,任何一个采集通道出现异常，则模组状态为异常
     * @return  工作状态
     */
    public Integer getStatus() {
        return getChannels().stream().anyMatch(channel -> channel.getStatus() == ChannelStatus.FAULT) ? ChannelStatus.FAULT : ChannelStatus.NORMAL;
    }
}
