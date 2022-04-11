package com.tcb.common.model;

import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.model.parameter.ComponentParameter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yuhai
 *
 * 联维项目的设备组模型
 */
@Getter
@Setter
public class Device extends ConfigModel<ComponentEntity, ComponentParameter> {

    private Set<DeviceModule> deviceModules = new HashSet<>(0);

    public Device(ComponentEntity entity) {
        super(entity);
        Assert.isTrue(entity.getType() == ComponentType.DEVICE, "entity type must be DEVICE_GROUP");
    }

    public void addDeviceModule(DeviceModule deviceModule){
        deviceModules.add(deviceModule);
    }

    public void removeDeviceModule(DeviceModule deviceModule){
        deviceModules.remove(deviceModule);
    }

    public Set<DeviceModule> getDeviceModules(){
        return deviceModules;
    }

    public DeviceModule getDeviceModule(String id){
        return deviceModules.stream()
                .filter(deviceModule -> deviceModule.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public MeasurePoint getMeasurePoint(String id){
        return deviceModules.stream()
                .map(deviceModule -> deviceModule.getMeasurePoint(id))
                .filter(measurePoint -> measurePoint != null)
                .findFirst()
                .orElse(null);
    }

    public String getOrgId(){
        return entity.getOrgId();
    }

    @Override
    public void update(ComponentEntity entity) {
        super.update(entity);
        removeInvalidDeviceModules(entity);
        updateDeviceModules(entity);
    }

    // 更新设备下的组件
    private void updateDeviceModules(ComponentEntity entity) {
        entity.getChildren().forEach(componentEntity -> {
            DeviceModule deviceModule = getDeviceModule(componentEntity.getId());
            if(deviceModule == null){
                deviceModule = new DeviceModule(this, componentEntity);
                addDeviceModule(deviceModule);
            }
            deviceModule.update(componentEntity);
        });
    }

    // 删除无效的组件
    private void removeInvalidDeviceModules(ComponentEntity entity) {
        Set<String> deviceModuleIds = entity.getChildren().stream().map(ComponentEntity::getId).collect(Collectors.toSet());
        Set<DeviceModule> unusedDeviceModules = deviceModules.stream().filter(deviceModule -> !deviceModuleIds.contains(deviceModule.getId())).collect(Collectors.toSet());
        deviceModules.remove(unusedDeviceModules);
    }

    /**
     * 获取设备状态
     * @return  设备状态
     * 设备状态定义为设备下所有组件的状态的最高级
     */
    public Integer getStatus() {
        return deviceModules.stream().map(DeviceModule::getStatus).max(Integer::compareTo).orElse(AlarmStatus.NORMAL);
    }

    public List<String> getUsers(){
        return entity.getUsers();
    }

    /**
     * 设备是否处于监测状态中
     * @return  true：处于监测状态中；false：不处于监测状态中
     */
    public boolean isMonitoring() {
        return getDeviceModules().stream().anyMatch(measurePoint -> measurePoint.isMonitoring());
    }

    /**
     * 组设备当前报警持续时长
     * @return
     */
    public long getDuration() {
        return getDeviceModules().stream().map(DeviceModule::getDuration).max(Long::compareTo).orElse(0L);
    }
}
