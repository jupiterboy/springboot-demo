package com.tcb.common.constant;

public class MonitorEventType {

    public static final int UNKNOWN_EVENT = 0;
    public static final int ALARM_EVENT = 1;
    public static final int ALARM_CANCEL_EVENT = 2;
    public static final int ALARM_REPEAT_EVENT = 3;
    public static final int COLLECTOR_CONNECTION_EVENT = 11;
    public static final int CHANNEL_VALUE_EVENT = 21;
    public static final int CHANNEL_FAULT_EVENT = 22;
    public static final int MEASURE_VALUE_EVENT = 31;
    public static final int MONITOR_VALUE_EVENT = 41;


    public static String getName(int type){
        switch(type){
            case ALARM_EVENT : return "报警事件";
            case ALARM_CANCEL_EVENT: return "报警取消事件";
            case ALARM_REPEAT_EVENT: return "连续报警事件";
            case COLLECTOR_CONNECTION_EVENT: return "采集器连接事件";
            case CHANNEL_VALUE_EVENT: return "通道值事件";
            case CHANNEL_FAULT_EVENT: return "通道故障事件";
            case MEASURE_VALUE_EVENT: return "测点值事件";
            case MONITOR_VALUE_EVENT: return "监控值事件";
            default:
                return "未知";
        }
    }
}
