package com.tcb.common.model.checker;

import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.model.MonitorItem;

/**
 * @author yuhai
 *
 * 正常状态检查器
 *
 * @see AlarmStatus
 */
public class NormalAlarmStatusChecker extends ConstantAlarmStatusChecker {

    public NormalAlarmStatusChecker(MonitorItem monitorItem) {
        super(monitorItem, AlarmStatus.NORMAL);
    }
}
