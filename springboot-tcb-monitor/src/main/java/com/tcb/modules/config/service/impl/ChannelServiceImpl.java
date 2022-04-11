package com.tcb.modules.config.service.impl;

import com.tcb.common.entity.ChannelEntity;
import com.tcb.common.entity.MeasurePointEntity;
import com.tcb.common.model.Channel;
import com.tcb.common.repository.ChannelRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Slf4j
@Service("com.tcb.modules.config.service.impl.ChannelServiceImpl")
public class ChannelServiceImpl extends BaseServiceImpl<ChannelRepository, ChannelEntity, String> implements ChannelService {

    @Override
    public ChannelEntity saveEntity(ChannelEntity channelEntity) {
        Assert.notNull(channelEntity.getCollectorEntity(), "没有关联采集器");
        channelEntity = repository.save(channelEntity);
        collectorManager.updateCollector(channelEntity.getCollectorEntity().getId());
        return channelEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        Channel channel = collectorManager.getChannel(id);
        if(channel != null) {
            collectorManager.updateCollector(channel.getCollector().getId());
        }
    }

    @Override
    public void updateEntity(ChannelEntity channelEntity) {
        Assert.notNull(channelEntity.getCollectorEntity(), "没有关联采集器");
        channelEntity.setUpdateTime(new Date());
        repository.save(channelEntity);
        collectorManager.updateCollector(channelEntity.getCollectorEntity().getId());
    }

    @Override
    public void bind(String channelId, String measurePointId) {
        ChannelEntity channelEntity = collectorManager.getChannel(channelId).getEntity();
        MeasurePointEntity measurePointEntity = deviceManager.getMeasurePoint(measurePointId).getEntity();
        if (channelEntity != null && measurePointEntity != null) {
            channelEntity.setMeasurePointEntity(measurePointEntity);
            repository.save(channelEntity);
            collectorManager.updateCollector(channelEntity.getCollectorEntity().getId());
        }else{
            log.error("channelId:{}, measurePointId:{}",channelId,measurePointId);
        }

    }

    @Override
    public void unbind(String channelId, String measurePointId) {
        ChannelEntity channelEntity = collectorManager.getChannel(channelId).getEntity();
        MeasurePointEntity measurePointEntity = deviceManager.getMeasurePoint(measurePointId).getEntity();
        if (channelEntity != null && measurePointEntity != null) {
            channelEntity.setMeasurePointEntity(null);
            repository.save(channelEntity);
            collectorManager.updateCollector(channelEntity.getCollectorEntity().getId());
        }else{
            log.error("channelId:{}, measurePointId:{}",channelId,measurePointId);
        }
    }
}
