package com.tcb.modules.config.service.impl;

import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.model.Device;
import com.tcb.common.repository.ComponentRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service("com.tcb.modules.config.service.impl.DeviceServiceImpl")
public class DeviceServiceImpl extends BaseServiceImpl<ComponentRepository, ComponentEntity, String> implements DeviceService {

    @Override
    public ComponentEntity saveEntity(ComponentEntity componentEntity) {
        Assert.isTrue(componentEntity.getType() == ComponentType.DEVICE, "组件类型错误");
        componentEntity = repository.save(componentEntity);
        deviceManager.updateDevice(componentEntity.getId());
        return componentEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        deviceManager.removeDevice(id);
    }

    @Override
    public void updateEntity(ComponentEntity componentEntity) {
        Assert.isTrue(componentEntity.getType() == ComponentType.DEVICE, "组件类型错误");
        componentEntity.setUpdateTime(new Date());
        repository.save(componentEntity);
        deviceManager.updateDevice(componentEntity.getId());
    }

    @Override
    public List<ComponentEntity> findAllEntities() {
        return repository.findByType(ComponentType.DEVICE);
    }

    @Override
    public List<ComponentEntity> findByOrgId(String orgId) {
        Set<Device> devices = deviceManager.getDevices(orgId);
        if(devices != null && !devices.isEmpty()){
            return deviceManager.getDevices(orgId).stream().map(Device::getEntity).collect(java.util.stream.Collectors.toList());
        }
        return repository.findByOrgId(orgId);
    }
}
