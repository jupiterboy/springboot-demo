package com.tcb.common.model.checker;

import com.tcb.common.model.MonitorItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractAlarmStatusChecker implements AlarmStatusChecker{

    /**
     * 告警对应的监测项
     */
    private MonitorItem monitorItem;
}
