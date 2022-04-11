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
 * 连续报警事件
 */
@Getter
@Setter
public class AlarmRepeatEvent extends SpringApplicationEvent {

    private final MonitorValue monitorValue;

    public AlarmRepeatEvent(MonitorItem monitorItem, MonitorValue monitorValue) {
        super(monitorItem, MonitorEventType.ALARM_REPEAT_EVENT);
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
