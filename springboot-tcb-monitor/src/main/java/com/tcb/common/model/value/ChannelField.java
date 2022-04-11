package com.tcb.common.model.value;

import com.tcb.common.model.Channel;

import java.io.Serializable;

/**
 * @author yuhai
 *
 * 采集值接口, 具体实现类填充完数据后转换为ChannelValue对象
 *
 * @see ChannelValue.TemperatureValue
 */
public interface ChannelField extends Serializable {

    ChannelValue toChannelValue(Channel channel);
}
