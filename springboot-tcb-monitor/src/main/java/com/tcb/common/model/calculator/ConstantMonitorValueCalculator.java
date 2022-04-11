package com.tcb.common.model.calculator;

import com.tcb.common.model.MonitorItem;
import com.tcb.common.model.value.MeasureValue;

/**
 * @author yuhai
 *
 * 常量值计算器，返回固定的常量值
 *
 * @see MonitorValueCalculator
 */
public class ConstantMonitorValueCalculator extends AbstractMonitorValueCalculator {

    /**
     * 设定的固定常量值
     */
    private final double value;

    public ConstantMonitorValueCalculator(MonitorItem monitorItem, double value) {
        super(monitorItem);
        this.value = value;
    }

    @Override
    public double calculate(MeasureValue measureValue) {
        return value;
    }
}
