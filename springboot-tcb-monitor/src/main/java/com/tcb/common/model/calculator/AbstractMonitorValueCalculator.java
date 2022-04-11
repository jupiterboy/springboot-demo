package com.tcb.common.model.calculator;

import com.tcb.common.model.MonitorItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractMonitorValueCalculator implements MonitorValueCalculator {

    /**
     * 计算监测数值的监测项
     */
    private MonitorItem monitorItem;

}
