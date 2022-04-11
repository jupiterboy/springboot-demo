package com.tcb.modules.config.service.impl;

import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.model.DeviceModule;
import com.tcb.common.repository.ComponentRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.DeviceModuleBaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service("com.tcb.modules.config.service.impl.DeviceModuleServiceImpl")
public class DeviceModuleServiceImpl extends BaseServiceImpl<ComponentRepository, ComponentEntity, String> implements DeviceModuleBaseService {

    @Override
    public ComponentEntity saveEntity(ComponentEntity componentEntity) {
        Assert.isTrue(componentEntity.getType() == ComponentType.DEVICE_MODULE, "组件类型错误");
        Assert.notNull(componentEntity.getParent(), "没有关联设备");
        componentEntity = repository.save(componentEntity);
        deviceManager.updateDevice(componentEntity.getParent().getId());
        return componentEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        DeviceModule deviceModule = deviceManager.getDeviceModule(id);
        if(deviceModule != null) {
            deviceManager.updateDevice(deviceModule.getDevice().getId());
        }
    }

    @Override
    public void updateEntity(ComponentEntity componentEntity) {
        Assert.isTrue(componentEntity.getType() == ComponentType.DEVICE_MODULE, "组件类型错误");
        Assert.notNull(componentEntity.getParent(), "没有关联设备");
        componentEntity.setUpdateTime(new Date());
        repository.save(componentEntity);
        deviceManager.updateDevice(componentEntity.getParent().getId());
    }

    @Override
    public List<ComponentEntity> findAllEntities() {
        return repository.findByType(ComponentType.DEVICE_MODULE);
    }
}
