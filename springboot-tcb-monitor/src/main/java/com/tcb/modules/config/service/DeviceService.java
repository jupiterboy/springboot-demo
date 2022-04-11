package com.tcb.modules.config.service;

import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.service.BaseService;

import java.util.List;

public interface DeviceService extends BaseService<ComponentEntity, String> {

    List<ComponentEntity> findByOrgId(String orgId);
}
