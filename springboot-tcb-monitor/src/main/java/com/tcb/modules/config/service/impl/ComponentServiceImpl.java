package com.tcb.modules.config.service.impl;

import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.model.Component;
import com.tcb.common.repository.ComponentRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.ComponentService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Service("com.tcb.modules.config.service.impl.ComponentServiceImpl")
public class ComponentServiceImpl extends BaseServiceImpl<ComponentRepository, ComponentEntity, String> implements ComponentService {

    @Override
    public ComponentEntity saveEntity(ComponentEntity componentEntity) {
        Assert.isTrue(componentEntity.getType() == ComponentType.COMPONENT, "组件类型错误");
        Assert.notNull(componentEntity.getParent(), "没有关联组件");
        componentEntity = repository.save(componentEntity);
        deviceManager.updateDevice(componentEntity.getParent().getParent().getId());
        return componentEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        Component component = deviceManager.getComponent(id);
        if(component != null) {
            deviceManager.updateDevice(component.getDeviceModule().getDevice().getId());
        }
    }

    @Override
    public void updateEntity(ComponentEntity componentEntity) {
        Assert.isTrue(componentEntity.getType() == ComponentType.COMPONENT, "组件类型错误");
        componentEntity.setUpdateTime(new Date());
        repository.save(componentEntity);
        deviceManager.updateDevice(componentEntity.getParent().getParent().getId());
    }

    @Override
    public List<ComponentEntity> findAllEntities() {
        return repository.findByType(ComponentType.COMPONENT);
    }
}
