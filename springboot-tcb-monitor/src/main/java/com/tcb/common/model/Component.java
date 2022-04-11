package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.entity.MeasurePointEntity;
import com.tcb.common.model.manager.MeasurePointManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuhai
 *
 * 联维项目的部件模型
 */
@Getter
@Setter
public class Component extends MeasurePointManager {

    /**
     * 所属设备组件
     */
    @JsonIgnore
    private DeviceModule deviceModule;

    public Component(DeviceModule deviceModule, ComponentEntity entity) {
        super(entity);
        Assert.isTrue(entity.getType() == ComponentType.COMPONENT, "component type must be COMPONENT");
        this.deviceModule = deviceModule;
        // 从缓存初始化时，entity直接没有自动进行关联，需要手动关联
        entity.setParent(deviceModule.getEntity());
    }

    @Override
    public void update(ComponentEntity entity) {
        super.update(entity);
        removeInvalidMeasurePoints(entity);
        updateMeasurePoints(entity);
    }

    // 更新测点
    private void updateMeasurePoints(ComponentEntity entity) {
        entity.getMeasurePointEntities().forEach(measurePointEntity -> {
            MeasurePoint measurePoint = getMeasurePoint(measurePointEntity.getId());
            if(measurePoint == null){
                measurePoint = new MeasurePoint(this, measurePointEntity);
                addMeasurePoint(measurePoint);
            }
            measurePoint.update(measurePointEntity);
        });
    }

    // 删除无效的测点
    private void removeInvalidMeasurePoints(ComponentEntity entity) {
        Set<String> measurePointIds = entity.getMeasurePointEntities().stream().map(MeasurePointEntity::getId).collect(Collectors.toSet());
        Set<MeasurePoint> unusedMeasurePoints = getMeasurePoints().stream().filter(measurePoint -> !measurePointIds.contains(measurePoint.getId())).collect(Collectors.toSet());
        getMeasurePoints().removeAll(unusedMeasurePoints);
    }

    /**
     * 获取部件状态
     * @return 部件状态
     * 部件状态定义为部件下所有测点的状态的最高级
     */
    public Integer getStatus() {
        return getMeasurePoints().stream().map(MeasurePoint::getStatus).max(Integer::compareTo).orElse(AlarmStatus.NORMAL);
    }

    /**
     * 部件是否处于监测中
     * @return  true：处于监测中，false：不处于监测中
     */
    public boolean isMonitoring() {
        return getMeasurePoints().stream().anyMatch(measurePoint -> measurePoint.isMonitoring());
    }

    /**
     * 部件当前报警持续时长
     * @return
     */
    public long getDuration() {
        return getMeasurePoints().stream().map(MeasurePoint::getDuration).max(Long::compareTo).orElse(0L);
    }
}
