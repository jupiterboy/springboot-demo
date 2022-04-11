package com.tcb.common.model.checker;

import com.tcb.common.constant.AlarmStatus;

/**
 * @author yuhai
 *
 * 告警状态检查器，根据监测项和计算的监测值结果判断告警状态
 *
 * @see AlarmStatus
 */
public interface AlarmStatusChecker {

    /**
     * 根据监测项和计算的监控值结果判断是否告警
     * @param value 监控数值
     * @return  告警状态（正常、预警、报警）
     */
    int checkStatus(double value);

}
