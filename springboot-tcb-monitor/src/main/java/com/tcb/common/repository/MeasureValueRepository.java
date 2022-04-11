package com.tcb.common.repository;

import com.tcb.common.entity.MeasureValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeasureValueRepository extends JpaRepository<MeasureValueEntity, Long> {

    List<MeasureValueEntity> findByDeviceNumberAndMeasurePointNumber(String deviceNumber, String measurePointNumber);
}

