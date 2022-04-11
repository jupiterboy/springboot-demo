package com.tcb.common.repository;

import com.tcb.common.entity.MeasurePointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurePointRepository extends JpaRepository<MeasurePointEntity, String> {

//    List<MeasurePointEntity> findByComponentId(String componentId);
}

