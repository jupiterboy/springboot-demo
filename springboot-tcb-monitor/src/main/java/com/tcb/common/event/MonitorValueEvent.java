package com.tcb.common.event;

import com.tcb.common.constant.MonitorEventType;
import com.tcb.common.constant.MonitorItemType;
import com.tcb.common.model.Device;
import com.tcb.common.model.MeasurePoint;
import com.tcb.common.model.MonitorItem;
import com.tcb.common.model.value.MonitorValue;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuhai
 *
 * 监测值数据产生事件
 */
@Setter
@Getter
public class MonitorValueEvent extends SpringApplicationEvent {

    /**
     * 监控值
     */
    private MonitorValue monitorValue;

    public MonitorValueEvent(MonitorItem monitorItem, MonitorValue monitorValue) {
        super(monitorItem, MonitorEventType.MONITOR_VALUE_EVENT);
        this.monitorValue = monitorValue;
    }

    @Override
    public String getSourceId() {
        MonitorItem monitorItem = (MonitorItem) getSource();
        return monitorItem.getId();
    }

    @Override
    public String getSourceName() {
        MonitorItem monitorItem = (MonitorItem) getSource();
        MeasurePoint measurePoint = monitorItem.getMeasurePoint();
        Device device = measurePoint.getDevice();
        return String.format("设备: %s, 测点: %s, 监测项: %s", device.getNumber(), measurePoint.getNumber(), MonitorItemType.getName(monitorItem.getType()));
    }
}
