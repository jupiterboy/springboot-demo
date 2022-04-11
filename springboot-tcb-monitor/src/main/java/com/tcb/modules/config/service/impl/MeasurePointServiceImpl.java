package com.tcb.modules.config.service.impl;

import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.MeasurePointEntity;
import com.tcb.common.model.Component;
import com.tcb.common.model.DeviceModule;
import com.tcb.common.model.MeasurePoint;
import com.tcb.common.repository.MeasurePointRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.MeasurePointService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service("com.tcb.modules.config.service.impl.MeasurePointServiceImpl")
public class MeasurePointServiceImpl extends BaseServiceImpl<MeasurePointRepository, MeasurePointEntity, String> implements MeasurePointService {

    @Override
    public MeasurePointEntity saveEntity(MeasurePointEntity measurePointEntity) {
        Assert.notNull(measurePointEntity.getComponentEntity(), "没有关联组件或部件");
        measurePointEntity = repository.save(measurePointEntity);
        String deviceId = getDeviceId(measurePointEntity);
        deviceManager.updateDevice(deviceId);
        return measurePointEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        MeasurePoint measurePoint = deviceManager.getMeasurePoint(id);
        if(measurePoint != null) {
            String deviceId = getDeviceId(measurePoint);
            deviceManager.updateDevice(deviceId);
        }

        // 刷新测点关联的通道
        if(measurePoint.getChannel() != null) {
            String collectorId = measurePoint.getChannel().getCollector().getId();
            collectorManager.updateCollector(collectorId);
            // 重新关联通道和测点
            collectorManager.initChannelBind();
        }
    }

    @Override
    public void updateEntity(MeasurePointEntity measurePointEntity) {
        Assert.notNull(measurePointEntity.getComponentEntity(), "没有关联组件或部件");
        measurePointEntity.setUpdateTime(new Date());
        repository.save(measurePointEntity);
        String deviceId = getDeviceId(measurePointEntity);
        deviceManager.updateDevice(deviceId);

        // 刷新测点关联的通道
        if(measurePointEntity.getChannelEntity() != null) {
            String collectorId = measurePointEntity.getChannelEntity().getCollectorEntity().getId();
            collectorManager.updateCollector(collectorId);
        }
    }

    private String getDeviceId(MeasurePoint measurePoint) {
        String deviceId = null;
        if(measurePoint.getMeasurePointManager() instanceof DeviceModule) {
            deviceId = ((DeviceModule) measurePoint.getMeasurePointManager()).getDevice().getId();
        }
        if(measurePoint.getMeasurePointManager() instanceof Component) {
            deviceId = ((Component) measurePoint.getMeasurePointManager()).getDeviceModule().getDevice().getId();
        }
        return deviceId;
    }

    private String getDeviceId(MeasurePointEntity measurePointEntity) {
        String deviceId = null;
        if(measurePointEntity.getComponentEntity().getType() == ComponentType.DEVICE_MODULE) {
            deviceId = measurePointEntity.getComponentEntity().getParent().getId();
        }
        if(measurePointEntity.getComponentEntity().getType() == ComponentType.COMPONENT) {
            deviceId = measurePointEntity.getComponentEntity().getParent().getParent().getId();
        }
        return deviceId;
    }
}
