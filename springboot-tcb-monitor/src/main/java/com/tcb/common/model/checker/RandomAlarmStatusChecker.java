package com.tcb.common.model.checker;

import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.model.MonitorItem;

import java.util.Random;

/**
 * @author yuhai
 *
 * 随机状态检查器，随机返回告警的不同状态
 *
 * @see AlarmStatus
 */
public class RandomAlarmStatusChecker extends AbstractAlarmStatusChecker {

    public RandomAlarmStatusChecker(MonitorItem monitorItem) {
        super(monitorItem);
    }

    @Override
    public int checkStatus(double value) {
        return new Random().nextInt(3);
    }
}
