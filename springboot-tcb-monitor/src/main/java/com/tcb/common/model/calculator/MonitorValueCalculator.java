package com.tcb.common.model.calculator;

import com.tcb.common.model.value.MeasureValue;

/**
 * @author yuhai
 *
 * 监测值计算器，根据采集到的测量值计算监测数值
 *
 */
public interface MonitorValueCalculator {

    /**
     * 计算监测值
     * @param measureValue 采集的测量值
     * @return  监测数值
     */
    double calculate(MeasureValue measureValue);

}
