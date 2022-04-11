package com.tcb.common.repository;

import com.tcb.common.entity.MonitorValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MonitorValueRepository extends JpaRepository<MonitorValueEntity, Long> {

    // 获取具体设备的组件或者部件在一段时间的监测值
    List<MonitorValueEntity> findByDeviceNumberAndMeasurePointNumberAndCreateTimeBetween(String deviceNumber, String measurePointNumber, Date start, Date end);

    // 获取具体设备的组件或者部件下某个监测项在一段时间的监测值
    List<MonitorValueEntity> findByDeviceNumberAndMeasurePointNumberAndMonitorItemTypeAndCreateTimeBetween(String deviceNumber, String measurePointNumber, int monitorItemType, Date start, Date end);

    // 分页，根据组件或部件id获取所有报警记录
    Page<MonitorValueEntity> findByDeviceNumberAndMeasurePointNumberAndMonitorItemTypeAndCreateTimeBetween(String deviceNumber, String measurePointNumber, int monitorItemType, Date start, Date end, Pageable pageable);
}

