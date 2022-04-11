package com.tcb.common.model.generator;

import com.tcb.common.constant.ChannelType;
import com.tcb.common.model.Channel;
import com.tcb.common.model.value.ChannelValue;

public class FixedChannelValueGenerator implements ChannelValueGenerator {

    private final double value;

    public FixedChannelValueGenerator() {
        this(1.0D);
    }

    public FixedChannelValueGenerator(double value) {
        this.value = value;
    }

    @Override
    public ChannelValue generate(Channel channel) {
        ChannelValue channelValue = null;
        if (channel.getType() == ChannelType.VIBRATION) {
            channelValue = ChannelValue.VibrationValue.builder()
                    .value1(value)
                    .value2(value)
                    .build()
                    .toChannelValue(channel);
        }else if(channel.getType() == ChannelType.TEMPERATURE) {
            channelValue = ChannelValue.TemperatureValue.builder()
                    .value1(value)
                    .value2(value)
                    .build()
                    .toChannelValue(channel);
        }else{
            channelValue = ChannelValue.builder().build();
        }
        return channelValue;
    }
}
