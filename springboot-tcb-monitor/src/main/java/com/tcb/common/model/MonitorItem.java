package com.tcb.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.AlarmStatus;
import com.tcb.common.constant.MonitorItemType;
import com.tcb.common.entity.MonitorItemEntity;
import com.tcb.common.model.calculator.MonitorValuerCalculatorFactory;
import com.tcb.common.model.checker.AlarmStatusCheckerFactory;
import com.tcb.common.model.parameter.MonitorItemParameter;
import com.tcb.common.model.value.MeasureValue;
import com.tcb.common.model.value.MonitorValue;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author yuhai
 *
 * 监测项模型，监测测点下测量数据中某个具体的数据指标。
 *
 * 一个测点可以有多个监测项，每个监测项对应唯一具体的一种监测值。
 *
 * 监测项可以创建监测值和告警值
 */
@Getter
@Setter
public class MonitorItem extends ConfigModel<MonitorItemEntity, MonitorItemParameter>{

    /**
     * 所属测点
     */
    @JsonIgnore
    private MeasurePoint measurePoint;

    /**
     * 缓存最近的监测值
     */
    private MonitorValue monitorValue;

    public MonitorItem(MeasurePoint measurePoint, MonitorItemEntity entity) {
        super(entity);
        this.measurePoint = measurePoint;
        // 从缓存初始化时，entity直接没有自动进行关联，需要手动关联
        entity.setMeasurePointEntity(measurePoint.getEntity());
    }

    public MonitorValue createMonitorValue(MeasureValue measureValue){
        double value = calculateMonitorValue(measureValue);
        int status = checkStatus(value);
        MonitorValue monitorValue = MonitorValue.builder()
                .monitorItem(this)
                .measureValue(measureValue)
                .value(value)
                .status(status)
                .message(getMessage(status, value))
                .type(getType())
                .build();
        // 缓存最近的监测值
        this.monitorValue = monitorValue;
        return monitorValue;
    }

    public double calculateMonitorValue(MeasureValue measureValue){
        // 根据监测项的类型获取不同的监测值计算器
        return MonitorValuerCalculatorFactory.getMonitorValueCalculator(this).calculate(measureValue);
    }

    public int checkStatus(double value){
        // 根据监测项的类型获取不同的状态检测器
        return AlarmStatusCheckerFactory.getAlarmStatusChecker(this).checkStatus(value);
    }

    public Long getAlarmId() {
        return entity.getAlarmId();
    }

    public void setAlarmId(Long alarmId) {
        entity.setAlarmId(alarmId);
    }

    public Integer getStatus() {
        return entity.getStatus();
    }

    public void setStatus(Integer status) {
        entity.setStatus(status);
    }

    public int getCount() {
        return entity.getCount();
    }

    public void setCount(int count) {
        entity.setCount(count);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getAlarmTime() {
        return entity.getAlarmTime();
    }

    public void setAlarmTime(Date alarmTime) {
        entity.setAlarmTime(alarmTime);
    }

    // TODO: 修改报警消息内容
    private String getMessage(int status, double value){
        return String.format("设备: %s, 测点: %s, 监测项: %s, 状态: %s, 当前值: %.3f",
                getMeasurePoint().getDevice().getNumber(),
                getMeasurePoint().getNumber(),
                MonitorItemType.getName(getType()),
                AlarmStatus.getName(status),
                value);

    }

    /**
     * 监测项当前报警持续时长
     * @return  告警持续时间
     */
    public long getDuration(){
        if (getAlarmTime() == null) {
            return 0;
        }
        return (System.currentTimeMillis() - getAlarmTime().getTime())/1000;
    }

    @JsonIgnore
    public boolean isNormal(){
        return getStatus() == AlarmStatus.NORMAL;
    }
}
