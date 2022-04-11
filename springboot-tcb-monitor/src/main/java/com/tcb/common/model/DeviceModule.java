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

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuhai
 *
 * 联维项目的设备组件模型
 */
@Getter
@Setter
public class DeviceModule extends MeasurePointManager {

    @JsonIgnore
    private Device device;

    private Set<Component> components = new HashSet<Component>();

    public DeviceModule(Device device, ComponentEntity entity) {
        super(entity);
        Assert.isTrue(entity.getType() == ComponentType.DEVICE_MODULE, "entity type must be DEVICE");
        this.device = device;
        // 从缓存初始化时，entity直接没有自动进行关联，需要手动关联
        entity.setParent(device.getEntity());
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public void removeComponent(Component component){
        components.remove(component);
    }

    public Set<Component> getComponents() {
        return components;
    }

    public Component getComponent(String id) {
        return components.stream()
                .filter(component -> component.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MeasurePoint getMeasurePoint(String id) {
        // 获取设备本身下的所有测点
        MeasurePoint mp = super.getMeasurePoint(id);

        if(mp == null){// 如果设备下没有找到，则查找设备下的所有组件的测点
            mp = getComponents().stream()
                    .map(component -> component.getMeasurePoint(id))
                    .filter(mp1 -> mp1 != null)
                    .findFirst()
                    .orElse(null);
        }
        return mp;
    }

    @Override
    public void update(ComponentEntity entity) {
        super.update(entity);
        removeInvalidComponents(entity);
        removeInvalidMeasurePoints(entity);
        updateComponents(entity);
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

    // 更新部件
    private void updateComponents(ComponentEntity entity) {
        entity.getChildren().forEach(componentEntity -> {
            Component component = getComponent(componentEntity.getId());
            if(component == null){
                component = new Component(this, componentEntity);
                addComponent(component);
            }
            component.update(componentEntity);
        });
    }

    // 删除无效的测点
    private void removeInvalidMeasurePoints(ComponentEntity entity) {
        Set<String> measurePointIds = entity.getMeasurePointEntities().stream().map(MeasurePointEntity::getId).collect(Collectors.toSet());
        Set<MeasurePoint> unusedMeasurePoints = getMeasurePoints().stream().filter(measurePoint -> !measurePointIds.contains(measurePoint.getId())).collect(Collectors.toSet());
        getMeasurePoints().removeAll(unusedMeasurePoints);
    }

    // 删除无效的部件
    private void removeInvalidComponents(ComponentEntity entity) {
        Set<String> componentIds = entity.getChildren().stream().map(ComponentEntity::getId).collect(Collectors.toSet());
        Set<Component> unusedComponents = components.stream().filter(component -> !componentIds.contains(component.getId())).collect(Collectors.toSet());
        components.removeAll(unusedComponents);
    }

    /**
     * 获取组件的状态
     * @return 组件的状态
     * 组件的状态定义为组件下所有测点和部件状态的最高级
     */
    public Integer getStatus() {
        int status1 = getMeasurePoints().stream().map(MeasurePoint::getStatus).max(Integer::compareTo).orElse(AlarmStatus.NORMAL);
        int status2 = components.stream().map(Component::getStatus).max(Integer::compareTo).orElse(AlarmStatus.NORMAL);
        return Math.max(status1, status2);
    }


    /**
     * 设备组是否处于状态监测中
     * @return 是否处于状态监测中
     */
    public boolean isMonitoring() {
        return getMeasurePoints().stream().anyMatch(measurePoint -> measurePoint.isMonitoring()) ||
                getComponents().stream().anyMatch(component -> component.isMonitoring());
    }


    /**
     * 组件当前报警持续时长
     * @return
     */
    public long getDuration() {
        long max1 = getMeasurePoints().stream().map(MeasurePoint::getDuration).max(Long::compareTo).orElse(0L);
        long max2 = getComponents().stream().map(Component::getDuration).max(Long::compareTo).orElse(0L);
        return Math.max(max1, max2);
    }
}
