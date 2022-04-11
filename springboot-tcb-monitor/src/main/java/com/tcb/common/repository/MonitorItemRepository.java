package com.tcb.common.repository;

import com.tcb.common.entity.MonitorItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorItemRepository extends JpaRepository<MonitorItemEntity, String> {

//    List<MonitorItemEntity> findByComponentId(String componentId);
}

