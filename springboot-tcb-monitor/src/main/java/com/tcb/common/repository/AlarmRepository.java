package com.tcb.common.repository;

import com.tcb.common.entity.AlarmCustomEntity;
import com.tcb.common.entity.AlarmEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<AlarmEntity, Long>, JpaSpecificationExecutor<AlarmEntity> {

    // 演示查询alarm表中某些字段数据，通过定义接口的方式实现
    List<AlarmCustomEntity> findByDeviceNumberAndMeasurePointNumberAndMonitorItemType(String deviceNumber, String measurePointNumber, Integer monitorItemType);

    List<AlarmEntity> findByDeviceNumberAndMeasurePointNumberAndMonitorItemTypeAndStatus(String deviceNumber, String measurePointNumber, Integer monitorItemType, Integer status);

    // 单项的模糊查询
    default Page<AlarmEntity> findByDeviceNumberAndMeasurePointNumberAndMonitorItemTypeAndStatusAndCreateTimeBetween(String deviceNumber, String measurePointNumber, Integer monitorItemType, Integer status, Date startTime, Date endTime, Pageable pageable){
        Specification<AlarmEntity> specification = new Specification<AlarmEntity>() {

            @Override
            public Predicate toPredicate(Root<AlarmEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(deviceNumber != null && ! "".equals(deviceNumber)) {
                    predicateList.add(criteriaBuilder.equal(root.get("deviceNumber").as(String.class), deviceNumber));
                }
                if(measurePointNumber != null && ! "".equals(measurePointNumber)) {
                    predicateList.add(criteriaBuilder.equal(root.get("measurePointNumber").as(String.class), measurePointNumber));
                }
                if(monitorItemType != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("monitorItemType").as(Integer.class), monitorItemType));
                }
                if(status != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
                }
                if(startTime != null && endTime == null) {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startTime));
                }
                if(startTime == null && endTime != null) {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endTime));
                }
                if(startTime != null && endTime != null) {
                    predicateList.add(criteriaBuilder.between(root.get("createTime").as(Date.class), startTime, endTime));
                }
                Predicate[] pre = new Predicate[predicateList.size()];
                pre = predicateList.toArray(pre);
                return query.where(pre).getRestriction();
            }
        };
        return findAll(specification, pageable);
    }

    // 多项的模糊查询，含In查询
    default Page<AlarmEntity> findByDeviceNumberAndMeasurePointNumberInAndMonitorItemTypeAndStatusAndCreateTimeBetween(String deviceNumber, List<String> measurePointNumbers, List<Integer> monitorItemTypes, Integer status, Date startTime, Date endTime, Pageable pageable){
        Specification<AlarmEntity> specification = new Specification<AlarmEntity>() {

            @Override
            public Predicate toPredicate(Root<AlarmEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if(deviceNumber != null && ! "".equals(deviceNumber)) {
                    predicateList.add(criteriaBuilder.equal(root.get("deviceNumber").as(String.class), deviceNumber));
                }
                if(measurePointNumbers != null && !measurePointNumbers.isEmpty()) {
                    predicateList.add(root.get("measurePointNumber").as(String.class).in(measurePointNumbers));
                }
                if(monitorItemTypes != null && !monitorItemTypes.isEmpty()) {
                    predicateList.add(root.get("monitorItemType").as(Integer.class).in(monitorItemTypes));
                }
                if(status != null) {
                    predicateList.add(criteriaBuilder.equal(root.get("status").as(Integer.class), status));
                }
                if(startTime != null && endTime == null) {
                    predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime").as(Date.class), startTime));
                }
                if(startTime == null && endTime != null) {
                    predicateList.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime").as(Date.class), endTime));
                }
                if(startTime != null && endTime != null) {
                    predicateList.add(criteriaBuilder.between(root.get("createTime").as(Date.class), startTime, endTime));
                }
                Predicate[] pre = new Predicate[predicateList.size()];
                pre = predicateList.toArray(pre);
                return query.where(pre).getRestriction();
            }
        };
        return findAll(specification, pageable);
    }

    AlarmEntity findTopByDeviceNumberAndMeasurePointNumberAndMonitorItemTypeOrderByCreateTimeDesc(String deviceNumber, String measurePointNumber, Integer monitorItemType);

    // TODO: 根据监测项获取告警记录
}

