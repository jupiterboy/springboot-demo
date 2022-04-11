package com.tcb.common.constant;

/**
 * @author yuhai
 *
 * 通道类型定义
 */
public class ChannelType {

    /**
     * 未知通道
     */
     public static final int UNKNOWN = 0;

     /**
     * 振动通道
     */
    public static final int VIBRATION = 1;

    /**
     * 温度通道
     */
    public static final int TEMPERATURE = 2;

    // TODO: 定义具体的通道类型

    public static String getName(int channelType) {
        switch (channelType) {
            case VIBRATION:
                return "振动";
            case TEMPERATURE:
                return "温度";
            default:
                return "未知";
        }
    }
}
