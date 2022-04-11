package com.tcb.common.repository;

import com.tcb.common.entity.CollectorFaultLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectorFaultLogRepository extends JpaRepository<CollectorFaultLogEntity, String> {

    List<CollectorFaultLogEntity> findByCollectorNumber(String collectorNumber);

    List<CollectorFaultLogEntity> findByCollectorNumberAndChannelNumber(String collectorNumber, String channelNumber);

    CollectorFaultLogEntity findTopByCollectorNumberAndChannelNumberOrderByCreateTimeDesc(String collectorNumber, String channelNumber);
}
