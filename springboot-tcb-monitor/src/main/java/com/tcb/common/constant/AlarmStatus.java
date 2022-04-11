package com.tcb.common.constant;

/**
 * @author yuhai
 * 告警状态
 */
public class AlarmStatus {

    /**
     * 正常状态
     */
    public static final int NORMAL = 0;

    /**
     * 预警状态
     */
    public static final int EARLY_WARNING = 1;

    /**
     * 告警状态
     */
    public static final int WARNING = 2;

    public static String getName(int status) {
    	String name = "";
    	switch (status) {
        case NORMAL:
            name = "正常";
            break;
        case EARLY_WARNING:
            name = "预警";
            break;
        case WARNING:
            name = "告警";
            break;
        default:
            name = "未知";
            break;
        }
    	return name;
    }
}
