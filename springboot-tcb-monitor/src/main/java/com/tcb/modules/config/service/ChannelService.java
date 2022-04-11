package com.tcb.modules.config.service;

import com.tcb.common.entity.ChannelEntity;
import com.tcb.common.service.BaseService;

public interface ChannelService extends BaseService<ChannelEntity, String> {

    void bind(String channelId, String measurePointId);

    void unbind(String channelId, String measurePointId);
}
