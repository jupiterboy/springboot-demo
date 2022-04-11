package com.tcb.common.repository;

import com.tcb.common.entity.CollectorConnectionLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectorConnectionLogRepository extends JpaRepository<CollectorConnectionLogEntity, String> {

    CollectorConnectionLogEntity findTopByCollectorNumberOrderByCreateTimeDesc(String collectorNumber);
}
