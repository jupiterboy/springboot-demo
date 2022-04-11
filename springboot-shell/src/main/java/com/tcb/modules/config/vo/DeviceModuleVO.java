package com.tcb.modules.config.vo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DeviceModuleVO {

    public String id;
    public String name;
    public String number;
    public Set<ComponentVO> components = new HashSet<>();
    public Set<MeasurePointVO> measurePoints = new HashSet<>();

}
