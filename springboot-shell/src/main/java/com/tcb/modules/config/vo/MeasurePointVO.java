package com.tcb.modules.config.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MeasurePointVO {

    public String id;
    public String name;
    public String number;
    public Set<MonitorItemVO> items = new HashSet<>();

}
