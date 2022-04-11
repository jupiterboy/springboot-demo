package com.tcb.common.model.checker;

import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.model.MonitorItem;

/**
 * @author yuhai
 *
 * 固定状态检查器
 *
 * @see AlarmStatus
 */
public class ConstantAlarmStatusChecker extends AbstractAlarmStatusChecker {

    /**
     * 告警状态
     */
    private int status = AlarmStatus.NORMAL;

    public ConstantAlarmStatusChecker(MonitorItem monitorItem, int status) {
        super(monitorItem);
        this.status = status;
    }

    @Override
    public int checkStatus(double value) {
        return status;
    }
}
