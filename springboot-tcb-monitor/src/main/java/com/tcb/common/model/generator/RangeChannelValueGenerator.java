package com.tcb.common.model.generator;

import com.tcb.common.constant.ChannelType;
import com.tcb.common.model.Channel;
import com.tcb.common.model.value.ChannelValue;

import java.util.Random;

public class RangeChannelValueGenerator implements ChannelValueGenerator {

    private static final Random random = new Random();

    private final double min;

    private final double max;

    public RangeChannelValueGenerator() {
        this.min = 0.0D;
        this.max = 100.0D;
    }

    public RangeChannelValueGenerator(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public ChannelValue generate(Channel channel) {
        ChannelValue channelValue = null;
        if (channel.getType() == ChannelType.VIBRATION) {
            channelValue = ChannelValue.VibrationValue.builder()
                    .value1(random.doubles(min,max).findFirst().getAsDouble())
                    .value2(random.doubles(min,max).findFirst().getAsDouble())
                    .build()
                    .toChannelValue(channel);
        }else if(channel.getType() == ChannelType.TEMPERATURE) {
            channelValue = ChannelValue.TemperatureValue.builder()
                    .value1(random.doubles(min,max).findFirst().getAsDouble())
                    .value2(random.doubles(min,max).findFirst().getAsDouble())
                    .build()
                    .toChannelValue(channel);
        }else{
            channelValue = ChannelValue.builder().build();
        }
        return channelValue;
    }
}
