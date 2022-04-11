package com.tcb.modules.config.service.impl;

import com.tcb.common.entity.CollectorEntity;
import com.tcb.common.model.Collector;
import com.tcb.common.repository.CollectorRepository;
import com.tcb.common.service.impl.BaseServiceImpl;
import com.tcb.modules.config.service.CollectorService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("com.tcb.modules.config.service.impl.CollectorServiceImpl")
public class CollectorServiceImpl extends BaseServiceImpl<CollectorRepository, CollectorEntity, String> implements CollectorService {

    @Override
    public List<CollectorEntity> findByOrgId(String orgId) {
        Set<Collector> collectors = collectorManager.getCollectors(orgId);
        if(collectors != null && !collectors.isEmpty()) {
            return collectors.stream().map(Collector::getEntity).collect(Collectors.toList());
        }
        return repository.findByOrgId(orgId);
    }

    @Override
    public CollectorEntity saveEntity(CollectorEntity collectorEntity) {
//        Assert.notNull(collectorEntity.getOrgId(), "没有关联组织机构");
        collectorEntity = repository.save(collectorEntity);
        collectorManager.updateCollector(collectorEntity.getId());
        return collectorEntity;
    }

    @Override
    public void deleteEntity(String id) {
        repository.deleteById(id);
        collectorManager.removeCollector(id);
    }

    @Override
    public void updateEntity(CollectorEntity collectorEntity) {
        //        Assert.notNull(collectorEntity.getOrgId(), "没有关联组织机构");
        collectorEntity.setUpdateTime(new Date());
        getRepository().save(collectorEntity);
        collectorManager.updateCollector(collectorEntity.getId());
    }
}