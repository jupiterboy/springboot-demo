package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tcb.common.constant.ChannelStatus;
import com.tcb.common.entity.ChannelEntity;
import com.tcb.common.entity.CollectorEntity;
import com.tcb.common.entity.ModuleEntity;
import com.tcb.common.model.manager.ChannelManager;
import com.tcb.common.model.parameter.CollectorParameter;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuhai
 *
 * 采集器模型，映射实际的采集器
 */
@Getter
@Setter
public class Collector extends ChannelManager<CollectorEntity, CollectorParameter> {

    /**
     * 采集器下的模块
     */
    private final Set<CollectorModule> collectorModules = new HashSet<>();

    /**
     * 采集器连接状态
     */
    private Boolean online = false;

    /**
     * 记录采集器本次上线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date onlineTime;

    /**
     * 记录采集器本次下线时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date offlineTime;

    public Collector(CollectorEntity entity) {
        super(entity);
    }

    /**
     * 获取模块ID获取模块
     * @param id 模块id
     * @return 采集模块
     */
    public CollectorModule getCollectorModule(String id) {
        return collectorModules.stream()
                .filter(collectorModule -> collectorModule.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * 返回所有采集模块
     * @return 采集模块集合
     */
    public Set<CollectorModule> getCollectorModules() {
        return collectorModules;
    }

    /**
     * 添加采集模块
     * @param collectorModule 采集模块
     */
    public void addCollectorModule(CollectorModule collectorModule) {
        collectorModules.add(collectorModule);
    }

    /**
     * 删除采集模块
     * @param collectorModule 采集模块
     */
    public void removeCollectorModule(CollectorModule collectorModule) {
        collectorModules.remove(collectorModule);
    }

    /**
     * 返回采集器所属的组织机构id
     * @return  组织机构id
     */
    public String getOrgId() {
        return entity.getOrgId();
    }

    /**
     * 更新采集器下的实体类
     * @param entity
     */
    @Override
    public void update(CollectorEntity entity) {
        super.update(entity);
        removeInvalidChannels(entity);
        removeInvalidCollectorModules(entity);
        updateChannels(entity);
        updateCollectorModules(entity);
    }

    // 更新采集器模块
    private void updateCollectorModules(CollectorEntity entity) {
        entity.getModuleEntities().forEach(moduleEntity -> {
            CollectorModule collectorModule = getCollectorModule(moduleEntity.getId());
            if(collectorModule == null) {
                collectorModule = new CollectorModule(this, moduleEntity);
                addCollectorModule(collectorModule);
            }
            collectorModule.update(moduleEntity);
        });
    }

    // 更新通道
    private void updateChannels(CollectorEntity entity) {
        entity.getChannelEntities().forEach(channelEntity -> {
            Channel channel = getChannel(channelEntity.getId());
            if(channel == null) {
                channel = new Channel(this, channelEntity);
                addChannel(channel);
            }
            channel.update(channelEntity);
        });
    }

    // 删除无效的模块
    private void removeInvalidCollectorModules(CollectorEntity entity) {
        Set<String> collectorModuleIds = entity.getModuleEntities().stream().map(ModuleEntity::getId).collect(Collectors.toSet());
        Set<CollectorModule> unusedCollectorModules = collectorModules.stream().filter(collectorModule -> !collectorModuleIds.contains(collectorModule.getId())).collect(Collectors.toSet());
        collectorModules.removeAll(unusedCollectorModules);
    }

    // 删除无效的通道
    private void removeInvalidChannels(CollectorEntity entity) {
        Set<String> channelIds = entity.getChannelEntities().stream().map(ChannelEntity::getId).collect(Collectors.toSet());
        Set<Channel> unusedChannels = getChannels().stream().filter(channel -> !channelIds.contains(channel.getId())).collect(Collectors.toSet());
        getChannels().removeAll(unusedChannels);
    }

    public CollectorModule getCollectorModuleByNumber(String number) {
        return collectorModules.stream()
                .filter(collectorModule -> collectorModule.getNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    public void setOnline(Boolean online) {
        this.online = online;
        if(online) {
            this.onlineTime = new Date();
            this.offlineTime = null;
        }else{
            this.offlineTime = new Date();
            // 采集器下线, 通道关联的测点失去监控
            getChannels().stream().map(channel -> channel.getMeasurePoint())
                    .filter(measurePoint -> measurePoint != null)
                    .forEach(measurePoint -> measurePoint.setMonitoring(false));
        }
    }

    /**
     * 获取采集器本次在线时长
     * @return 采集器本次在线时长，单位：秒
     */
    public long getDuration(){
        if (onlineTime == null) {
            return 0;
        }
        return (System.currentTimeMillis() - onlineTime.getTime())/1000;
    }

    /**
     * 获取采集器的工作状态,任何一个采集通道出现异常，则采集器状态为异常
     * @return  工作状态
     */
    public Integer getStatus() {
        return getChannels().stream().anyMatch(channel -> channel.getStatus() == ChannelStatus.FAULT) ? ChannelStatus.FAULT : ChannelStatus.NORMAL;
    }
}
