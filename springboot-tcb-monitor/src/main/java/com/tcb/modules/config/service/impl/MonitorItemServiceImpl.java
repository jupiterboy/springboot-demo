package com.tcb.modules.config.service.impl;

import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.MeasurePointEntity;
import com.tcb.common.entity.MonitorItemEntity;
import com.tcb.common.model.Component;
import com.tcb.common.model.DeviceModule;
import com.tcb.common.model.MeasurePoint;
import com.tcb.common.model.MonitorItem;
import com.tcb.common.repository.MonitorItemRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.MonitorItemService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service("com.tcb.modules.config.service.impl.MonitorItemServiceImpl")
public class MonitorItemServiceImpl extends BaseServiceImpl<MonitorItemRepository, MonitorItemEntity, String> implements MonitorItemService {

    @Override
    public MonitorItemEntity saveEntity(MonitorItemEntity monitorItemEntity) {
        Assert.notNull(monitorItemEntity.getMeasurePointEntity(), "没有关联测点");
        monitorItemEntity = repository.save(monitorItemEntity);
        String deviceId = getDeviceId(monitorItemEntity.getMeasurePointEntity());
        deviceManager.updateDevice(deviceId);
        return monitorItemEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        MonitorItem monitorItem = deviceManager.getMonitorItem(id);
        if(monitorItem != null) {
            String deviceId = getDeviceId(monitorItem.getMeasurePoint());
            deviceManager.updateDevice(deviceId);
        }
    }

    @Override
    public void updateEntity(MonitorItemEntity monitorItemEntity) {
        Assert.notNull(monitorItemEntity.getMeasurePointEntity(), "没有关联测点");
        monitorItemEntity.setUpdateTime(new Date());
        repository.save(monitorItemEntity);
        String deviceId = getDeviceId(monitorItemEntity.getMeasurePointEntity());
        deviceManager.updateDevice(deviceId);
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
