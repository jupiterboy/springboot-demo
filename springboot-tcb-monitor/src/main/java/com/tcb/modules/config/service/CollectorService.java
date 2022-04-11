package com.tcb.modules.config.service;

import com.tcb.common.entity.CollectorEntity;
import com.tcb.common.service.BaseService;

import java.util.List;

public interface CollectorService extends BaseService<CollectorEntity, String> {

    List<CollectorEntity> findByOrgId(String orgId) ;

}
