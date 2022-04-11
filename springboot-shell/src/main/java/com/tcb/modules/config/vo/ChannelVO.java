package com.tcb.modules.config.vo;

import lombok.Data;

@Data
public class ChannelVO {

    public String id;
    public String name;
    public String number;
    public MeasurePointVO measurePoint;

}
