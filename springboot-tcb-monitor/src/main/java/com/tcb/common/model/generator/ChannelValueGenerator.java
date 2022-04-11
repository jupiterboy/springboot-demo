package com.tcb.common.model.generator;

import com.tcb.common.model.Channel;
import com.tcb.common.model.value.ChannelValue;


public interface ChannelValueGenerator{

    ChannelValue generate(Channel channel);
}
