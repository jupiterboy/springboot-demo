package com.tcb.common.constant;

/**
 * @author yuhai
 *
 * 采集器通道故障定义
 */
public class ChannelStatus {

    public static final int NORMAL = 0;

    public static final int FAULT = 1;

    // TODO: 定义具体的通道故障类型

    public static String getName(int status) {
        switch (status) {
        case NORMAL:
            return "正常";
        case FAULT:
            return "故障";
        default:
            return "未知";
        }
    }
}
