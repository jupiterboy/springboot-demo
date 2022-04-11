package com.tcb.common.event;

import com.tcb.common.constant.MonitorEventType;
import com.tcb.common.model.Channel;
import com.tcb.common.model.Collector;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yuhai
 * 采集器通道数据产生事件
 */
@Getter
@Setter
public class ChannelFaultEvent extends SpringApplicationEvent {

    /**
     * 采集器通道故障类型
     */
    private int faultType;

    public ChannelFaultEvent(Channel channel, int faultType) {
        super(channel, MonitorEventType.CHANNEL_FAULT_EVENT);
        this.faultType = faultType;
    }

    @Override
    public String getSourceId() {
        Channel channel = (Channel) source;
        return channel.getId();
    }

    @Override
    public String getSourceName() {
        Collector collector = ((Channel) source).getCollector();
        Channel channel = (Channel) source;
        return String.format("采集器: %s, 通道: %s", collector.getNumber(), channel.getNumber());
    }
}
