package com.tcb.common.model.calculator;

import com.tcb.common.model.MonitorItem;
import com.tcb.common.model.value.MeasureValue;

import java.util.Random;

/**
 * @author: yuhai
 *
 * 随机值计算器，用于模拟产生随机的监测值
 *
 * @see MonitorValueCalculator
 */
public class RandomMonitorValueCalculator extends AbstractMonitorValueCalculator {

    public RandomMonitorValueCalculator(MonitorItem monitorItem) {
        super(monitorItem);
    }

    @Override
    public double calculate(MeasureValue measureValue) {
        return new Random().nextDouble();
    }
}
