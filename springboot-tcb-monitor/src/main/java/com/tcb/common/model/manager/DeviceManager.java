package com.tcb.common.model.manager;

import com.tcb.common.constant.CacheKeyPrefix;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.model.*;
import com.tcb.common.utils.JsonUtils;
import com.tcb.modules.config.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@org.springframework.stereotype.Component
public class DeviceManager {

    @Autowired
    private DeviceService deviceService;

    private Set<Device> devices = new HashSet<>();

    @Autowired
    private StringRedisTemplate template;

    @PostConstruct
    public void init(){
        flushCache();
        loadDevices();
    }

    public List<ComponentEntity> loadDevices(){
        List<ComponentEntity> componentEntities = Collections.EMPTY_LIST;
        int size = template.keys(CacheKeyPrefix.DEVICE_ENTITY + "*").size();
        long start = System.currentTimeMillis();
        if(size == 0){
            componentEntities = deviceService.findAllEntities();
            log.info("load device entities from db.");
            componentEntities.forEach(componentEntity -> {
                updateDevice(componentEntity);
                updateCache(componentEntity);
            });
        }else{
            componentEntities = template.keys(CacheKeyPrefix.DEVICE_ENTITY + "*").stream()
                    .map(key -> template.opsForValue().get(key))
                    .filter(value -> value != null)
                    .map(value -> JsonUtils.fromJson(value, ComponentEntity.class))
                    .collect(Collectors.toList());
            log.info("load device entities from redis.");
            componentEntities.forEach(componentEntity -> {
                updateDevice(componentEntity);
            });
        }
        log.info("load device data success. cost: {}ms" , (System.currentTimeMillis() - start));
        return componentEntities;
    }

    public void addDevice(Device device){
        devices.add(device);
    }

    public void removeDevice(String id){
        devices.removeIf(device -> device.getId().equals(id));
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public Device getDevice(String id){
        return devices.stream()
                .filter(device -> device.getId().equals(id)).findFirst()
                .orElse(null);
    }

    public Device takeDevice(String deviceNumber){
        return devices.stream()
                .filter(device -> device.getNumber().equals(deviceNumber)).findFirst()
                .orElse(null);
    }

    public DeviceModule getDeviceModule(String id){
        return devices.stream()
                .flatMap(device -> device.getDeviceModules().stream())
                .filter(deviceModule -> deviceModule.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Component getComponent(String id){
        return devices.stream()
                .flatMap(device -> device.getDeviceModules().stream())
                .flatMap(deviceModule -> deviceModule.getComponents().stream())
                .filter(component -> component.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public MeasurePoint getMeasurePoint(String id){
        // 先遍历设备部件下的测点, 如果没有找到, 再遍历组件下的测点
        MeasurePoint measurePoint = devices.stream()
                .flatMap(device -> device.getDeviceModules().stream())
                .flatMap(deviceModule -> deviceModule.getMeasurePoints().stream())
                .filter(mp -> mp.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (measurePoint == null){
            measurePoint = devices.stream()
                    .flatMap(device -> device.getDeviceModules().stream())
                    .flatMap(deviceModule -> deviceModule.getComponents().stream())
                    .flatMap(component -> component.getMeasurePoints().stream())
                    .filter(mp -> mp.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return measurePoint;
    }


    public MeasurePoint takeMeasurePoint(String deviceNumber, String measurePointNumber){
        // 先遍历设备部件下的测点, 如果没有找到, 再遍历组件下的测点
        MeasurePoint measurePoint = devices.stream()
                .filter(device -> device.getNumber().equals(deviceNumber))
                .flatMap(device -> device.getDeviceModules().stream())
                .flatMap(deviceModule -> deviceModule.getMeasurePoints().stream())
                .filter(mp -> mp.getNumber().equals(measurePointNumber))
                .findFirst()
                .orElse(null);
        if (measurePoint == null){
            measurePoint = devices.stream()
                    .filter(device -> device.getNumber().equals(deviceNumber))
                    .flatMap(device -> device.getDeviceModules().stream())
                    .flatMap(deviceModule -> deviceModule.getComponents().stream())
                    .flatMap(component -> component.getMeasurePoints().stream())
                    .filter(mp -> mp.getNumber().equals(measurePointNumber))
                    .findFirst()
                    .orElse(null);
        }
        return measurePoint;
    }

    public MonitorItem getMonitorItem(String id){
        // 先遍历组件下的监测项
        MonitorItem monitorItem = devices.stream()
                .flatMap(device -> device.getDeviceModules().stream())
                .flatMap(deviceModule -> deviceModule.getMeasurePoints().stream())
                .flatMap(measurePoint -> measurePoint.getMonitorItems().stream())
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
        // 没有找到，再遍历部件下的监测项
        if (monitorItem == null){
            monitorItem = devices.stream()
                    .flatMap(device -> device.getDeviceModules().stream())
                    .flatMap(deviceModule -> deviceModule.getComponents().stream())
                    .flatMap(component -> component.getMeasurePoints().stream())
                    .flatMap(measurePoint -> measurePoint.getMonitorItems().stream())
                    .filter(item -> item.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return monitorItem;
    }

    public Set<Device> getDevices(String ordId){
        return devices.stream()
                .filter(device -> device.getOrgId().equals(ordId))
                .collect(Collectors.toSet());
    }

    public void updateDevice(String deviceId){
        ComponentEntity componentEntity = deviceService.getEntity(deviceId);
        updateDevice(componentEntity);
        updateCache(componentEntity);
//        System.out.println(JsonUtils.toPrettyJson(componentEntity));
    }

    private void updateCache(ComponentEntity componentEntity) {
        String key = CacheKeyPrefix.DEVICE_ENTITY + ":" + componentEntity.getId();
        String value = JsonUtils.toJson(componentEntity);
        template.opsForValue().set(key, value);
    }

    private void updateDevice(ComponentEntity componentEntity) {
        if (componentEntity != null){
            Device device = getDevice(componentEntity.getId());
            if (device == null){
                device = new Device(componentEntity);
                addDevice(device);
            }
            device.update(componentEntity);
        }
    }

    public void flushCache(){
        template.keys(CacheKeyPrefix.DEVICE_ENTITY + "*").forEach(key -> {
            template.delete(key);
        });
    }

    public void updateCache(){
        flushCache();
        loadDevices();
    }

    /**
     * 检查设备编号是否存在
     * @param number    设备编号
     * @return  true: 存在，false: 不存在
     */
    public boolean contains(String number) {
        return devices.stream()
                .anyMatch(device -> device.getNumber().equals(number));
    }

    public List<Device> getDevicesByUserId(String userId) {
        return devices.stream().filter(device -> device.getEntity().getUsers().contains(userId)).collect(Collectors.toList());
    }

    public MonitorItem takeMonitorItem(String deviceNumber, String measurePointNumber, int monitorItemType) {
        // 先遍历组件下的监测项
        MonitorItem monitorItem = devices.stream()
                .filter(device -> device.getNumber().equals(deviceNumber))
                .flatMap(device -> device.getDeviceModules().stream())
                .flatMap(deviceModule -> deviceModule.getMeasurePoints().stream())
                .filter(measurePoint -> measurePoint.getNumber().equals(measurePointNumber))
                .flatMap(measurePoint -> measurePoint.getMonitorItems().stream())
                .filter(item -> item.getType().equals(monitorItemType))
                .findFirst()
                .orElse(null);
        // 没有找到，再遍历部件下的监测项
        if (monitorItem == null){
            monitorItem = devices.stream()
                    .filter(device -> device.getNumber().equals(deviceNumber))
                    .flatMap(device -> device.getDeviceModules().stream())
                    .flatMap(deviceModule -> deviceModule.getComponents().stream())
                    .flatMap(component -> component.getMeasurePoints().stream())
                    .filter(measurePoint -> measurePoint.getNumber().equals(measurePointNumber))
                    .flatMap(measurePoint -> measurePoint.getMonitorItems().stream())
                    .filter(item -> item.getType().equals(monitorItemType))
                    .findFirst()
                    .orElse(null);
        }
        return monitorItem;
    }
}
