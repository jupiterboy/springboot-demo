package com.tcb.common.model.calculator;

import com.tcb.common.model.MonitorItem;

/**
 * @author yuhai
 *
 * 创建监测值计算工厂
 *
 * @see MonitorValueCalculator
 */
public class MonitorValuerCalculatorFactory {

    /**
     * 根据监测项目创建对应的监测值计算器
     * @param monitorItem  监测项
     * @return 监测值计算器
     */
    public static MonitorValueCalculator getMonitorValueCalculator(MonitorItem monitorItem) {
        // TODO 根据监测项目创建对应的监测值计算器
        return new RandomMonitorValueCalculator(monitorItem);
//        return new ConstantMonitorValueCalculator(monitorItem, 1.0);
    }
}
