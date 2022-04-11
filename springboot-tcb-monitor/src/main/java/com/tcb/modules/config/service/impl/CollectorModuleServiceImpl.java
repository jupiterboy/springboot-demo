package com.tcb.modules.config.service.impl;

import com.tcb.common.entity.ModuleEntity;
import com.tcb.common.model.CollectorModule;
import com.tcb.common.repository.ModuleRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.CollectorModuleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service("com.tcb.modules.config.service.impl.CollectorModuleServiceImpl")
public class CollectorModuleServiceImpl extends BaseServiceImpl<ModuleRepository, ModuleEntity, String> implements CollectorModuleService {

    @Override
    public ModuleEntity saveEntity(ModuleEntity moduleEntity) {
        Assert.notNull(moduleEntity.getCollectorEntity(), "没有关联采集器");
        moduleEntity = repository.save(moduleEntity);
        collectorManager.updateCollector(moduleEntity.getCollectorEntity().getId());
        return moduleEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        CollectorModule collectorModule = collectorManager.getCollectorModule(id);
        if(collectorModule != null) {
            collectorManager.updateCollector(collectorModule.getCollector().getId());
        }
    }

    @Override
    public void updateEntity(ModuleEntity moduleEntity) {
        Assert.notNull(moduleEntity.getCollectorEntity(), "没有关联采集器");
        moduleEntity.setUpdateTime(new Date());
        repository.save(moduleEntity);
        collectorManager.updateCollector(moduleEntity.getCollectorEntity().getId());
    }
}
