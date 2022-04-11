package com.tcb.common.model.manager;

import com.tcb.common.entity.ConfigEntity;
import com.tcb.common.model.Channel;
import com.tcb.common.model.ConfigModel;

import java.util.HashSet;
import java.util.Set;

public class ChannelManager<T extends ConfigEntity,P> extends ConfigModel<T,P> {

    private final Set<Channel> channels = new HashSet<>();

    public ChannelManager(T entity) {
        super(entity);
    }

    public Set<Channel> getChannels() {
        return channels;
    }

    public void addChannel(Channel channel) {
        channels.add(channel);
    }

    public void removeChannel(Channel channel) {
        channels.remove(channel);
    }

    public Channel getChannel(String id) {
        return channels.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}
