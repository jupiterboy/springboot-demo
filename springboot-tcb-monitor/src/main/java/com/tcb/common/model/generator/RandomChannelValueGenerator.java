package com.tcb.common.model.generator;

import com.tcb.common.constant.ChannelType;
import com.tcb.common.model.Channel;
import com.tcb.common.model.value.ChannelValue;

import java.util.Random;

public class RandomChannelValueGenerator implements ChannelValueGenerator {

    private static final Random random = new Random();

    @Override
    public ChannelValue generate(Channel channel) {
        ChannelValue channelValue = null;
        if (channel.getType() == ChannelType.VIBRATION) {
            channelValue = ChannelValue.VibrationValue.builder()
                    .value1(random.nextDouble())
                    .value2(random.nextDouble())
                    .build()
                    .toChannelValue(channel);
        }else if(channel.getType() == ChannelType.TEMPERATURE) {
            channelValue = ChannelValue.TemperatureValue.builder()
                    .value1(random.nextDouble())
                    .value2(random.nextDouble())
                    .build()
                    .toChannelValue(channel);
        }else{
            channelValue = ChannelValue.builder().build();
        }
        return channelValue;
    }
}
