package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.constant.ComponentType;
import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.entity.MeasurePointEntity;
import com.tcb.common.model.manager.MeasurePointManager;
import com.tcb.common.model.parameter.MeasurePointParameter;
import com.tcb.common.model.value.ChannelValue;
import com.tcb.common.model.value.MeasureValue;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * @author yuhai
 *
 * 测点模型，实际采集通道下传感器插入的设备点，该测点在整个设备下具有唯一的测点编号（如：1,2,3...)
 */
@Getter
@Setter
public class MeasurePoint extends ConfigModel<MeasurePointEntity, MeasurePointParameter>{

    private Set<MonitorItem> monitorItems = new HashSet<>();

    @JsonIgnore
    private Channel channel;

    /**
     * 测点管理器，测点的具体归属，测点可以归属于组件或者部件
     */
    @JsonIgnore
    private MeasurePointManager measurePointManager;

    /**
     * 缓存最近的测量值
     */
    private MeasureValue measureValue;

    /**
     * 测点是否处于监控中
     */
    private boolean monitoring;

    public MeasurePoint(MeasurePointManager measurePointManager, MeasurePointEntity entity) {
        super(entity);
        this.measurePointManager = measurePointManager;
        // 从缓存初始化时，entity直接没有自动进行关联，需要手动关联
        entity.setComponentEntity((ComponentEntity) measurePointManager.getEntity());
    }

    public void addMonitorItem(MonitorItem monitorItem) {
        monitorItems.add(monitorItem);
    }

    public void removeMonitorItem(MonitorItem monitorItem) {
        monitorItems.remove(monitorItem);
    }

    public Set<MonitorItem> getMonitorItems() {
        return monitorItems;
    }

    public MonitorItem getMonitorItem(String id) {
        return monitorItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public MeasureValue createMeasureValue(ChannelValue channelValue) {
        MeasureValue measureValue = channelValue.toMeasureValue(this);
        // 缓存最近的测量值
        this.measureValue = measureValue;
        return measureValue;
    }

    @Override
    public void update(MeasurePointEntity entity) {
        super.update(entity);
        removeInvalidMonitorItems(entity);
        updateMonitorItems(entity);
    }

    // 更新监测项
    private void updateMonitorItems(MeasurePointEntity entity) {
        entity.getMonitorItemEntities().forEach(monitorItemEntity -> {
            MonitorItem monitorItem = getMonitorItem(monitorItemEntity.getId());
            if (monitorItem == null) {
                monitorItem = new MonitorItem(this,monitorItemEntity);
                addMonitorItem(monitorItem);
            }
            monitorItem.update(monitorItemEntity);
        });
    }

    // 删除无效监测项
    private void removeInvalidMonitorItems(MeasurePointEntity entity) {
        Set<String> monitorItemIds = entity.getMonitorItemEntities().stream().filter(monitorItemEntity -> monitorItemEntity.getId() != null).map(monitorItemEntity -> monitorItemEntity.getId()).collect(Collectors.toSet());
        Set<MonitorItem> unusedMonitorItems = monitorItems.stream().filter(monitorItem -> !monitorItemIds.contains(monitorItem.getId())).collect(Collectors.toSet());
        monitorItems.removeAll(unusedMonitorItems);
    }

    /**
     * 返回测点状态，测点状态为测点下所有检测项状态的最大值
     * @return  测点状态
     */
    public Integer getStatus() {
       return monitorItems.stream().map(MonitorItem::getStatus).max(Integer::compareTo).orElse(AlarmStatus.NORMAL);
    }

    /**
     * 返回测点所属设备的设备id
     * @return  设备id
     */
    public String getDeviceId() {
        if(entity.getComponentEntity().getType() == ComponentType.COMPONENT) {
            return entity.getComponentEntity().getParent().getParent().getId();
        }
        if(entity.getComponentEntity().getType() == ComponentType.DEVICE_MODULE) {
            return entity.getComponentEntity().getParent().getId();
        }
        return null;
    }

    /**
     * 返回测点所属设备
     * @return  设备
     */
    @JsonIgnore
    public Device getDevice() {
        if(measurePointManager instanceof Component) {
            Component component = (Component) measurePointManager;
            return component.getDeviceModule().getDevice();
        }
        if(measurePointManager instanceof DeviceModule) {
            DeviceModule deviceModule = (DeviceModule) measurePointManager;
            return deviceModule.getDevice();
        }
        return null;
    }

    /**
     * 测点当前报警持续时长
     * @return
     */
    public long getDuration() {
        return getMonitorItems().stream().map(MonitorItem::getDuration).max(Long::compareTo).orElse(0L);
    }
}
