package com.tcb.common.model.checker;

import com.tcb.common.model.MonitorItem;

/**
 * @author yuhai
 *
 * 告警状态检查器工厂， 用于创建告警状态检查器
 */
public class AlarmStatusCheckerFactory {

    /**
     * 根据监测项目创建对应的告警状态检查器
     * @param monitorItem 监测项
     * @return 告警状态检查器
     */
    public static AlarmStatusChecker getAlarmStatusChecker(MonitorItem monitorItem) {
        // TODO:根据监测项类型定义不同的告警状态检查器
//        return new NormalAlarmStatusChecker();
        return new RandomAlarmStatusChecker(monitorItem);
    }
}
