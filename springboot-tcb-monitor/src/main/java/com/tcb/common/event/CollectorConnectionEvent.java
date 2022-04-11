package com.tcb.common.event;

import com.tcb.common.constant.MonitorEventType;
import com.tcb.common.model.Collector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectorConnectionEvent extends SpringApplicationEvent {

    private boolean connected;

    public CollectorConnectionEvent(Collector source, boolean connected) {
        super(source, MonitorEventType.COLLECTOR_CONNECTION_EVENT);
        this.connected = connected;
    }

    @Override
    public String getSourceId() {
        Collector collector = ((Collector) source);
        return collector.getId();
    }

    @Override
    public String getSourceName() {
        Collector collector = ((Collector) source);
        return String.format("采集器: %s %s", collector.getNumber(), connected ? "上线" : "下线");
    }
}
