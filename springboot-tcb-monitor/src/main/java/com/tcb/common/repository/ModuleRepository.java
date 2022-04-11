package com.tcb.common.repository;

import com.tcb.common.entity.ModuleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleEntity, String> {

//    List<ModuleEntity> findByCollectorId(String collectorId);
}

