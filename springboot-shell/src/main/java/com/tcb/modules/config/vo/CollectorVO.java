package com.tcb.modules.config.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class CollectorVO {

    public String id;
    public String name;
    public String number;
    public Set<ChannelVO> channels = new HashSet<>();
    public Set<CollectorModuleVO> modules = new HashSet<>();

}
